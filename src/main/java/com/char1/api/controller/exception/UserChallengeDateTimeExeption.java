package com.char1.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class UserChallengeDateTimeExeption extends RuntimeException {
    public UserChallengeDateTimeExeption() {
        super("userChallenge dates went wrong");
    }
}
