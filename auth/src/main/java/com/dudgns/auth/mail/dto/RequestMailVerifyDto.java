package com.dudgns.auth.mail.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RequestMailVerifyDto {
    private String email;
    private Long code;
}
