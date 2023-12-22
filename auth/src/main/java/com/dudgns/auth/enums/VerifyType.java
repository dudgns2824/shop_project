package com.dudgns.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VerifyType {
    REGISTER("REGISTER")
    ;
    private final String value;
}
