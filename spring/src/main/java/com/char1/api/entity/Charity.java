package com.char1.api.entity;

import javax.persistence.*;

@Entity
public class Charity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String name;
    private String description;
    private String linkToLogo;

    @OneToOne
    private BankAccount bankAccount;

    public Charity(){}

    public Charity(String name, String description, String linkToLogo) {
        this.name = name;
        this.description = description;
        this.linkToLogo = linkToLogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkToLogo() {
        return linkToLogo;
    }

    public void setLinkToLogo(String linkToLogo) {
        this.linkToLogo = linkToLogo;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "Charity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
