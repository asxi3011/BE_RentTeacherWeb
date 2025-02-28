package com.example.demo.service;

import com.example.demo.dto.request.RefreshTokenRequest;
import com.example.demo.dto.response.RefreshTokenResponse;
import com.example.demo.entity.RefreshToken;
import com.example.demo.enums.Token;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorApp;
import com.example.demo.mapper.RefreshTokenMapper;
import com.example.demo.repository.RefreshTokenRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RefreshTokenService {

    RefreshTokenRepository repository;
    RefreshTokenMapper mapper;

    public void save(RefreshTokenRequest request){

        repository.save(mapper.toRefreshToken(request));
    }

    public RefreshTokenResponse getRefreshToken(String id){
        RefreshToken refreshToken = repository.findById(id).orElseThrow(() -> new AppException(ErrorApp.REFRESH_TOKEN_NOT_FOUND));
        return mapper.toResponse(refreshToken);
    }

    void deleteRefreshToken (String id){
       repository.deleteById(id);
    }


}
