package com.juanprato.mytouch.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends Exception{

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public BadRequestException(String message) {
        super(message);
    }

}
