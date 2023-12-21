package com.dudgns.backendauth.exception;

public class EmailAlreadyExistsException extends IllegalArgumentException {
    public EmailAlreadyExistsException() {
        super("이미 존재 하는 이메일 입니다.");
    }
}
