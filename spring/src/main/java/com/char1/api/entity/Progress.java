package com.char1.api.entity;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
public class Progress {

    public Progress() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private DateTime entryDate;
    private int currentAmount;

    @ManyToOne
    private UserChallenge userChallenge;

    public DateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(DateTime entryDate) {
        this.entryDate = entryDate;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public UserChallenge getUserChallenge() {
        return userChallenge;
    }

    public void setUserChallenge(UserChallenge userChallenge) {
        this.userChallenge = userChallenge;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "entryDate=" + entryDate +
                ", currentAmount=" + currentAmount +
                '}';
    }
}
