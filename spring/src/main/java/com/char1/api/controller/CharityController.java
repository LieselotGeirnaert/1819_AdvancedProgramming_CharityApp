package com.char1.api.controller;

import com.char1.api.entity.Charity;
import com.char1.api.repository.CharityRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharityController {

    @Autowired
    CharityRepository charityRepository;

    @RequestMapping(value = "/charity", method = RequestMethod.GET)
    public Charity charity(@RequestParam(value = "id") String id) {
        return charityRepository.findById(Integer.parseInt(id));
    }

    @RequestMapping(value = "getAllCharities", method = RequestMethod.GET)
    public String getAllCharities() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(charityRepository.findAll());
    }

}