package com.char1.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnautherizedException extends RuntimeException {
    public UnautherizedException() {
        super("Unautherized Entry");
    }
}
