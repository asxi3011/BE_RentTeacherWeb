package com.example.demo.service;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.dto.response.IntrospectResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorApp;
import com.example.demo.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

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

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AuthenticationService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @Value("${jwt.SECRET_KEY}")
    @NonFinal
    String SIGN_KEY;

    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest) {
        User user = userRepository.findByUsername(authenticationRequest.getUsername()).
                orElseThrow(() -> new AppException(ErrorApp.USER_NOT_FOUND));
        var context = SecurityContextHolder.getContext().getAuthentication();

        String token = "";
        boolean isAuth = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if(isAuth) token = generateToken(user);
        return AuthenticationResponse.builder()
                .authenticated(isAuth)
                .token(token)
                .build();
    }

    ;

    public IntrospectResponse introspect(IntrospectRequest introspectRequest) {
        try {
            SignedJWT signJWT = SignedJWT.parse(introspectRequest.getToken());

            JWSVerifier verifier = new MACVerifier(SIGN_KEY);
            var context = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("NAME:" + context.getName());
            System.out.println("Principal" + context.getPrincipal().toString());
            context.getAuthorities().forEach(e -> System.out.println("AUTHOR" + e.getAuthority()));

            boolean isValidTime = signJWT.getJWTClaimsSet().getExpirationTime().after(new Date());
            System.out.println("VALID" + isValidTime);
            return IntrospectResponse
                    .builder()
                    .valid(signJWT.verify(verifier) && isValidTime)
                    .build();

        } catch (JOSEException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet =
                new JWTClaimsSet
                        .Builder()
                        .subject(user.getUsername())
                        .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
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
