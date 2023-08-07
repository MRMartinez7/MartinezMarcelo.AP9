package com.MindHub.homebanking.dto;

import com.MindHub.homebanking.Models.Account;

import java.time.LocalDate;

public class AccountDTO {
    private long Id;
    private String number;
    private LocalDate creationDate;
    private double balance;

    public AccountDTO(Account account) {
        Id = account.getId();
        number = account.getNumber();
        creationDate = account.getCreationDate();
        balance = account.getBalance();
    }

    public long getId() {
        return Id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getDate() {
        return creationDate;
    }

    public double getBalance() {
        return balance;
    }
}
