package com.dudgns.auth.exception;

public class NotVerifiedException extends RuntimeException {
    public NotVerifiedException(){
        super("이메일 인증이 완료 되지 않았습니다.");
    }
}
