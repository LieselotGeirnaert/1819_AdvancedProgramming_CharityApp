package com.char1.api.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Challenge {

    public Challenge() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String description;
    private String unitToMeasure;
    private int amountToComplete;

    @OneToMany
    private Set<Category> category;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitToMeasure() {
        return unitToMeasure;
    }

    public void setUnitToMeasure(String unitToMeasure) {
        this.unitToMeasure = unitToMeasure;
    }

    public int getAmountToComplete() {
        return amountToComplete;
    }

    public void setAmountToComplete(int amountToComplete) {
        this.amountToComplete = amountToComplete;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", unitToMeasure='" + unitToMeasure + '\'' +
                ", amountToComplete=" + amountToComplete +
                ", category=" + category +
                '}';
    }
}
