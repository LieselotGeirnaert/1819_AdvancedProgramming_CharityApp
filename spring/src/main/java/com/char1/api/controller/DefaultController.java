package com.char1.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping(value="/")
    public String showWelcomeMessage() {
        return "Welcome";
    }
}
