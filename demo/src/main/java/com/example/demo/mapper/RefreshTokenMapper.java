package com.example.demo.mapper;

import com.example.demo.dto.request.RefreshTokenRequest;
import com.example.demo.dto.response.RefreshTokenResponse;
import com.example.demo.entity.RefreshToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper {

    RefreshToken toRefreshToken(RefreshTokenRequest request);

    RefreshTokenResponse toResponse(RefreshToken refreshToken);
}
