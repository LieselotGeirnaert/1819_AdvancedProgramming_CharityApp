package com.char1.api.controller;

import com.char1.api.entity.Charity;
import com.char1.api.repository.CharityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CharityController {

    @Autowired
    CharityRepository charityRepository;

    @GetMapping(value = "/charity/{id}")
    public Charity charity(@PathVariable String id) {
        return charityRepository.findById(Integer.parseInt(id));
    }

    @GetMapping(value = "/charity")
    public List<Charity> getAllCharities() {
        return charityRepository.findAll();
    }

    @PostMapping("/charity")
    public Charity newCharity(@RequestBody Charity charity) {
        return charityRepository.save(charity);
    }
}
