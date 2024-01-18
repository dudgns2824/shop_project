package com.dudgns.auth.exception;

public class PasswordNotEnteredException extends RuntimeException {
    public PasswordNotEnteredException(){
        super("비밀번호가 입력 되지 않았습니다.");
    }
}
