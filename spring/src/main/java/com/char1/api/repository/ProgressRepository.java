package com.char1.api.repository;

import com.char1.api.entity.Progress;
import com.char1.api.entity.UserChallenge;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, String> {
    Progress findProgressById(int id);
    List<Progress> findAllProgressByUserChallenge(UserChallenge userChallenge);

}
