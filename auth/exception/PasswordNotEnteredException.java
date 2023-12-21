package com.dudgns.backendauth.exception;

public class PasswordNotEnteredException extends IllegalArgumentException {
    public PasswordNotEnteredException(){
        super("비밀번호가 입력 되지 않았습니다.");
    }
}
