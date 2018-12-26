package com.char1.api.request;


import java.time.LocalDateTime;

public class UserChallengeRequest {
    private int amountToComplete;
    private int amountToCompleteDaily;
    private int amountToDonate;
    private int challengeId;
    private int charityId;
    private boolean completeToDonate;
    private LocalDateTime deadlineDate;
    private LocalDateTime startDate;

    public int getAmountToComplete() {
        return amountToComplete;
    }

    public void setAmountToComplete(int amountToComplete) {
        this.amountToComplete = amountToComplete;
    }

    public int getAmountToDonate() {
        return amountToDonate;
    }

    public void setAmountToDonate(int amountToDonate) {
        this.amountToDonate = amountToDonate;
    }

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public int getCharityId() {
        return charityId;
    }

    public void setCharityId(int charityId) {
        this.charityId = charityId;
    }

    public boolean isCompleteToDonate() {
        return completeToDonate;
    }

    public void setCompleteToDonate(boolean completeToDonate) {
        this.completeToDonate = completeToDonate;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getAmountToCompleteDaily() {
        return amountToCompleteDaily;
    }

    public void setAmountToCompleteDaily(int amountToCompleteDaily) {
        this.amountToCompleteDaily = amountToCompleteDaily;
    }
}
