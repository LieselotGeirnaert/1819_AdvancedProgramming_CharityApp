package com.char1.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
public class Progress {

    public Progress() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private DateTime entryDate;
    private int currentAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_challenge_id")
    @JsonBackReference
    private UserChallenge userChallenge;

    @JsonIgnore
    public UserChallenge getUserChallenge() {
        return userChallenge;
    }

    public void setUserChallenge(UserChallenge userChallenge) {
        this.userChallenge = userChallenge;
    }

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

    @Override
    public String toString() {
        return "Progress{" +
                "entryDate=" + entryDate +
                ", currentAmount=" + currentAmount +
                '}';
    }
}
