package com.char1.api.repository;

import com.char1.api.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, String> {
    Progress findById(int id);
}
