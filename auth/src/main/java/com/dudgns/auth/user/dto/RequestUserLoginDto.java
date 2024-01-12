package com.dudgns.auth.user.dto;

import lombok.*;

@Data
public class RequestUserLoginDto {
    private String userId;
    private String password;
}
