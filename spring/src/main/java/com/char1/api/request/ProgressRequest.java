package com.char1.api.request;


public class ProgressRequest {
    private int userChallengeId;
    private int currentAmount;

    public int getUserChallengeId() {
        return userChallengeId;
    }

    public void setUserChallengeId(int userChallengeId) {
        this.userChallengeId = userChallengeId;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }
}
