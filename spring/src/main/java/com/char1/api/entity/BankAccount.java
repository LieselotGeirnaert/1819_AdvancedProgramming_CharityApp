package com.char1.api.entity;

import javax.persistence.*;

@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String BankAccount;

    public String getBankAccount() {
        return BankAccount;
    }

    public void setBankAccount(String bankAccount) {
        BankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "BankAccount='" + BankAccount + '\'' +
                '}';
    }

}
