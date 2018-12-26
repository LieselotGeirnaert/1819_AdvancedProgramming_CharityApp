package com.char1.api.service;

import com.char1.api.entity.UserChallenge;

public interface UserChallengeService {
    public UserChallenge getUserChallengeByIdSecure(int userChallengeId, String user);
}
