package com.dudgns.purchase.exception;

public class ItemNotExistException extends RuntimeException {
    public ItemNotExistException() {
        super("올바르지 않은 토큰 입니다.");
    }
}
