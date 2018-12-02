package com.char1.api.entity;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
public class UserChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private boolean completed;
    private boolean completeToDonate;
    private DateTime startDate;
    private DateTime deadlineDate;
    private int amountToDonate;

    @ManyToOne
    private Challenge challenge;

    @ManyToOne
    private User user;

    @ManyToOne
    private Charity charity;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isCompleteToDonate() {
        return completeToDonate;
    }

    public void setCompleteToDonate(boolean completeToDonate) {
        this.completeToDonate = completeToDonate;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(DateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public int getAmountToDonate() {
        return amountToDonate;
    }

    public void setAmountToDonate(int amountToDonate) {
        this.amountToDonate = amountToDonate;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }
}
