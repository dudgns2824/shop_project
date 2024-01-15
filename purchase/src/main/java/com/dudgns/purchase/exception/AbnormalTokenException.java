package com.dudgns.purchase.exception;

public class AbnormalTokenException extends IllegalArgumentException{
    public AbnormalTokenException() {
        super("올바르지 않은 토큰 입니다.");
    }
}
