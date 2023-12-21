package com.dudgns.backendauth.dto;

import com.dudgns.backendauth.enums.VerifyType;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RequestMailDto {
    private LocalDateTime requestTime;
    private String code;
    private VerifyType type;
    @Builder.Default
    private boolean verified = false;
    @Builder.Default
    private boolean confirm = false;
}
