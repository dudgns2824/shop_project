package com.dudgns.backendauth.mail.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ResponseMailVerifyDto {
    private String email;
    private Boolean success;
}
