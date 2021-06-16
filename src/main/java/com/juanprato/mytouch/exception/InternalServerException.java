package com.juanprato.mytouch.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends Exception {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public InternalServerException(String message) {
        super(message);
    }

}
