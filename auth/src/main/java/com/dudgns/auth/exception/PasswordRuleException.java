package com.dudgns.auth.exception;

public class PasswordRuleException extends IllegalArgumentException {
    public PasswordRuleException() {
        super("8-16자 영문, 숫자의 조합으로만 입력 가능합니다");
    }

}
