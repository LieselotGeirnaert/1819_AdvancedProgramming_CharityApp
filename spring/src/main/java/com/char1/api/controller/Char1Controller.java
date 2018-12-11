package com.char1.api.controller;

import com.char1.api.entity.Charity;
import com.char1.api.repository.CharityRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Char1Controller {

    @Autowired
    CharityRepository charityRepository;

    @GetMapping(value="/")
    public String welcome() {
        return "Welcome";
    }

    @GetMapping(value = "/charity/{id}")
    public String charity(@PathVariable String id) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(charityRepository.findById(Integer.parseInt(id)));
    }

    @GetMapping(value = "/charity")
    public String getAllCharities() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(charityRepository.findAll());
    }

    @PostMapping("/charity")
    public Charity newCharity(@RequestBody Charity charity) {
        return charityRepository.save(charity);
    }


}
