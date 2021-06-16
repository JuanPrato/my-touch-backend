package com.juanprato.mytouch.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception {

    private HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }
}
