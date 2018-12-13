package com.char1.api.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Challenge {

    public Challenge() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String description;
    private String unitToMeasure;
    private int amountToComplete;

    public String getLinkToLogo() {
        return linkToLogo;
    }

    public void setLinkToLogo(String linkToLogo) {
        this.linkToLogo = linkToLogo;
    }

    private String linkToLogo;

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

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
