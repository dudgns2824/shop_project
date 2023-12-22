package com.dudgns.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RolesEnum {
    USER("ROLE_USER"),
    VERIFIED("ROLE_VERIFIED"),
    BLOCK("ROLE_BLOCK"),
    ADMIN("ROLE_ADMIN")
    ;
    private final String value;
    
}
