package com.dudgns.auth.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("이미 존재 하는 이메일 입니다.");
    }
}
