package com.dudgns.auth.exception;

public class EmailVerifyMaxRequestException extends IllegalArgumentException{
    public EmailVerifyMaxRequestException(){
        super("이메일 인증 횟수가 초과 되었습니다.");
    }
}
