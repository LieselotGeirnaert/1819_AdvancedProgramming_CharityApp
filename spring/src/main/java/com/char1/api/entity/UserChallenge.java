package com.char1.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserChallenge {

    public UserChallenge() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private boolean completed;
    private boolean completeToDonate;
    private DateTime startDate;
    private DateTime deadlineDate;
    private int amountToDonate;
    private int amountToComplete;


    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name="challenge_id")
    private Challenge challenge;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="charity_id")
    private Charity charity;


    @OneToMany(mappedBy="userChallenge")
    @JsonManagedReference
    @JsonIgnore
    private List<Progress> progress;

    public void setProgress(List<Progress> progress) {
        this.progress = progress;
    }

    @JsonIgnore
    public List<Progress> getProgress() {
        return this.progress;
    }


    public String getProgressPercentage() {
        List<Progress> progresses = getProgress();
        int currentAmount = 0;
        for (Progress pr : progresses) {
            currentAmount += pr.getCurrentAmount();
        }
        return Float.toString(((float)currentAmount / (float)this.amountToComplete) * (float) 100);
    }


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
    
        public int getAmountToComplete() {
        return amountToComplete;
    }

    public void setAmountToComplete(int amountToComplete) {
        this.amountToComplete = amountToComplete;
    }

    @Override
    public String toString() {
        return "UserChallenge{" +
                "id=" + id +
                ", completed=" + completed +
                ", completeToDonate=" + completeToDonate +
                ", startDate=" + startDate +
                ", deadlineDate=" + deadlineDate +
                ", amountToDonate=" + amountToDonate +
                ", amountToComplete=" + amountToComplete +
                '}';
    }
}
