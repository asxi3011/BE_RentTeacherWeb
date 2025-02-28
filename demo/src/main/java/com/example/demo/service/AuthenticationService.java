package com.example.demo.service;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.dto.request.RefreshTokenRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.dto.response.IntrospectResponse;
import com.example.demo.entity.User;
import com.example.demo.enums.Token;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorApp;
import com.example.demo.mapper.RefreshTokenMapper;
import com.example.demo.repository.RefreshTokenRepository;
import com.example.demo.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AuthenticationService {

    UserRepository userRepository;
    RefreshTokenService refreshTokenService;
    PasswordEncoder passwordEncoder;
    @Value("${jwt.SECRET_KEY}")
    @NonFinal
    String SIGN_KEY;

    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest) {
        User user = userRepository.findByUsername(authenticationRequest.getUsername()).
                orElseThrow(() -> new AppException(ErrorApp.USER_NOT_FOUND));
        var context = SecurityContextHolder.getContext().getAuthentication();

        String accessToken = "";
        boolean isAuth = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if(isAuth){
             accessToken = generateToken(user,Token.ACCESS_TOKEN);
        }
        String refreshToken =  UUID.randomUUID().toString();
        RefreshTokenRequest tokenRequest = new RefreshTokenRequest(
                refreshToken,
                user.getUsername(),
                new Date(Instant.now().plus(Token.REFRESH_TOKEN.getDuration(),Token.REFRESH_TOKEN.getUnit()).toEpochMilli())
        );
        refreshTokenService.save(tokenRequest);

        return AuthenticationResponse.builder()
                .authenticated(isAuth)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }



    public IntrospectResponse introspect(IntrospectRequest introspectRequest) {
        try {
            String accessToken = introspectRequest.getAccessToken();
            SignedJWT signJWT = SignedJWT.parse(introspectRequest.getAccessToken());
            JWSVerifier verifier = new MACVerifier(SIGN_KEY);
            Boolean isNewToken = null;
            boolean isValidTimeAccess = signJWT.getJWTClaimsSet().getExpirationTime().after(new Date());
            String usernameFromInvalidAccessToken =  signJWT.getJWTClaimsSet().getSubject();

            if(!isValidTimeAccess){
                User user = userRepository.findByUsername(usernameFromInvalidAccessToken).orElseThrow(()->new AppException(ErrorApp.USER_NOT_FOUND));
                Date timeRefreshToken = refreshTokenService.getRefreshToken(introspectRequest.getRefreshToken()).getExpiryTime();
                boolean isValidTimeRefreshToken = timeRefreshToken.after(new Date());
                if(isValidTimeRefreshToken) {
                    isNewToken = true;
                    accessToken = generateToken(user,Token.ACCESS_TOKEN);
                    signJWT = SignedJWT.parse(accessToken);
                    isValidTimeAccess = signJWT.getJWTClaimsSet().getExpirationTime().after(new Date());
                }
            }

            return IntrospectResponse
                    .builder()
                    .valid(signJWT.verify(verifier) && (isValidTimeAccess ))
                    .accessToken(accessToken)
                    .isNewToken(isNewToken)
                    .build();

        } catch (JOSEException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void logout(IntrospectRequest introspectRequest){
        refreshTokenService.deleteRefreshToken(introspectRequest.getRefreshToken());
        // sau nay neu mo rong thi them blacklist roi them accessToken. Vi accessToken van truy cap dc
        // Hien gio vi xai Host free nen han che ghi du lieu
    }

    public String generateToken(User user, Token token) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet =
                new JWTClaimsSet
                        .Builder()
                        .subject(user.getUsername())
                        .expirationTime(new Date(Instant.now().plus(token.getDuration(), token.getUnit()).toEpochMilli()))
                        .claim("scope", buildScope(user))
                        .build();

        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject object = new JWSObject(header, payload);

        try {
            JWSSigner sign = new MACSigner(SIGN_KEY);
            object.sign(sign);
            return object.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

    }

    public String buildScope(User user) {
        final String prefixRole = "ROLE_";
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                        stringJoiner.add(prefixRole+role.getName());
                        role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                    }
            );

        }
        return stringJoiner.toString();
    }

}
