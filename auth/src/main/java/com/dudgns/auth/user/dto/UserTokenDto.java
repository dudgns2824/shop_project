package com.dudgns.auth.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserTokenDto {
    private UUID userGUID;
    private String accessToken; // 액세스 토큰 정보
    private String refreshToken; // 리프레시 토큰 정보
    private ZonedDateTime accessTokenExpiresAt; // 액세스 토큰 만료 일자
    private ZonedDateTime refreshTokenExpiresAt; // 리프레시 토큰 만료 일자
}
