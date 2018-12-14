package com.char1.api.controller;

import com.char1.api.controller.exception.DuplicateEntityException;
import com.char1.api.controller.exception.EntityNotFoundException;
import com.char1.api.entity.Charity;
import com.char1.api.repository.CharityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/charity")
public class CharityController {

    @Autowired
    CharityRepository charityRepository;

    @GetMapping(value = "/{id}")
    public Charity getCharity(@PathVariable int id) {
        if (!charityRepository.existsById(id)) throw new EntityNotFoundException();
        return charityRepository.findById(id);
    }

    @GetMapping
    public List<Charity> getAllCharities() {
        return charityRepository.findAll();
    }

    @PostMapping
    public Charity createCharity(@RequestBody Charity charity) {
        if (charityRepository.existsByName(charity.getName())) throw new DuplicateEntityException();
        return charityRepository.save(charity);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCharity(@PathVariable int id) {
        if (!charityRepository.existsById(id)) throw new EntityNotFoundException();
        charityRepository.deleteById(id);
        return;
    }
}
