package com.char1.api.repository;

import com.char1.api.entity.Charity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharityRepository extends JpaRepository<Charity, String> {

    public Charity findById(int id);
}
