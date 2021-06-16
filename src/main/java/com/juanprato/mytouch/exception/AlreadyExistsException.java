package com.juanprato.mytouch.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends Exception{

    private HttpStatus status = HttpStatus.CONFLICT;

    public AlreadyExistsException(String message) {
        super(message);
    }

}
