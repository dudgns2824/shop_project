package com.dudgns.auth.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RequestUserTokenDto {
    private String userGuid;
    private List<String> roles;
    @Deprecated
    private String tempTokenKey;        //기존에 쓰던 방식의 토큰
    
}
