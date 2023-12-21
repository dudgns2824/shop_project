package com.dudgns.backendauth.exception;

public class EmailVerifyFailedException extends IllegalArgumentException{
    public EmailVerifyFailedException() {
        super("이메일 인증에 실패 하였습니다.");
    }
}
