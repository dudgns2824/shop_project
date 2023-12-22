package com.dudgns.auth.common;

import java.util.UUID;

public class UuidStringUtil {
    public static UUID convertFromString(String str) {
        return UUID.fromString(str);
    }
}
