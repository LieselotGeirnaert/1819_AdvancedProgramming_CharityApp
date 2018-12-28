package com.char1.api.repository;

import com.char1.api.entity.User;
import com.char1.api.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface UserChallengeRepository extends JpaRepository<UserChallenge, Integer> {
    UserChallenge findById(int id);
    List<UserChallenge> findAllByUser(User user);
    List<UserChallenge> findAllByUserAndAndCompletedOrDeadlineDateBefore(User user, boolean completed, LocalDateTime deadline);
    List<UserChallenge> findAllByUserAndCompletedAndDeadlineDateAfterAndStartDateBefore(User user, boolean completed, LocalDateTime deadline, LocalDateTime startdate);
    void deleteById(int id);
}
