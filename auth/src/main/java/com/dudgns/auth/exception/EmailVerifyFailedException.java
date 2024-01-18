package com.dudgns.auth.exception;

public class EmailVerifyFailedException extends RuntimeException{
    public EmailVerifyFailedException() {
        super("이메일 인증에 실패 하였습니다.");
    }
}
