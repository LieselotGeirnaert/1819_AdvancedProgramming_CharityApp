package com.char1.api.entity;

import com.char1.api.utils.Char1Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.List;

import static javax.persistence.CascadeType.*;


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
    private LocalDateTime startDate;
    private LocalDateTime deadlineDate;
    private int amountToDonate;
    @Column(name = "amount_to_complete")
    private int amountToComplete = 0;
    private int amountToCompleteDaily;

    @ManyToOne(cascade={PERSIST, MERGE, REFRESH, DETACH})
    @JoinColumn(name="challenge_id")
    private Challenge challenge;

    @ManyToOne(cascade={MERGE, REFRESH, DETACH})
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade={PERSIST, MERGE, REFRESH, DETACH})
    @JoinColumn(name="charity_id")
    private Charity charity;


    @OneToMany(mappedBy="userChallenge", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<TotalProgress> totalProgress;

    @OneToMany(mappedBy="userChallenge", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<DailyProgress> dailyProgress;

    public void setTotalProgress(List<TotalProgress> progress) {
        this.totalProgress = progress;
    }

    @JsonIgnore
    public List<TotalProgress> getTotalProgress() {
        return this.totalProgress;
    }

    public Float getProgressPercentage() {
        return Char1Utils.calculateTotalProgress(this);
    }

    public Float getDailyProgressPercentage() {
        return Char1Utils.calculateDailyProgress(this);
    }

    public int getAmountToCompleteDaily() {
        return amountToCompleteDaily;
    }

    public void setAmountToCompleteDaily(int amountToCompleteDaily) {
        this.amountToCompleteDaily = amountToCompleteDaily;
    }

    @JsonIgnore
    public List<DailyProgress> getDailyProgress() {
        return dailyProgress;
    }

    public void setDailyProgress(List<DailyProgress> dailyProgress) {
        this.dailyProgress = dailyProgress;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDateTime deadlineDate) {
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
