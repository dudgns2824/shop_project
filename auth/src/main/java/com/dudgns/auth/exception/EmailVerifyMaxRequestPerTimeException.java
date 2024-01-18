package com.dudgns.auth.exception;

public class EmailVerifyMaxRequestPerTimeException extends RuntimeException{
    public EmailVerifyMaxRequestPerTimeException() {
        super("설정된 시간 당 메일 인증 요청 횟수 초과 입니다.");
    }
}
