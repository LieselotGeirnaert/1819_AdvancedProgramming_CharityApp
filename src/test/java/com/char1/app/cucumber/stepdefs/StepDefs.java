package com.char1.app.cucumber.stepdefs;

import com.char1.app.Char1App;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = Char1App.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
