package com.char1.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProgressException extends RuntimeException{
    public ProgressException(String error) {
        super("error: " + error);
    }
}