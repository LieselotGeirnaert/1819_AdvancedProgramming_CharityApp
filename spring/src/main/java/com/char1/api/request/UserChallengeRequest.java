package com.char1.api.request;

import org.joda.time.LocalDateTime;

public class UserChallengeRequest {
    private int amountToCoplete;
    private int amountToDonate;
    private int challengeId;
    private int charityId;
    private boolean completeToDonate;
    private LocalDateTime deadlineDate;
    private LocalDateTime startDate;

    public int getAmountToCoplete() {
        return amountToCoplete;
    }

    public void setAmountToCoplete(int amountToCoplete) {
        this.amountToCoplete = amountToCoplete;
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
}
