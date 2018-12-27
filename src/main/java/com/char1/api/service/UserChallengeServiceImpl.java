package com.char1.api.service;

import com.char1.api.controller.exception.EntityNotFoundException;
import com.char1.api.controller.exception.UnautherizedException;
import com.char1.api.entity.UserChallenge;
import com.char1.api.repository.UserChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserChallengeServiceImpl implements UserChallengeService {

    @Autowired
    UserChallengeRepository userChallengeRepository;

    public UserChallenge getUserChallengeByIdSecure(int userChallengeId, String user) {
        UserChallenge userChallenge;
        userChallenge = userChallengeRepository.findById(userChallengeId);

        if (userChallenge == null) throw new EntityNotFoundException("userChallenge");
        if (!user.equals(userChallenge.getUser().getEmailAddress())) {
            throw new UnautherizedException();
        }
        return userChallenge;
    }
}
