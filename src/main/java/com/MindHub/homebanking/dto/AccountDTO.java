package com.MindHub.homebanking.dto;

import com.MindHub.homebanking.Models.Account;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {
    private long Id;
    private String number;
    private LocalDate creationDate;
    private double balance;


    private Set<TransactionDTO> transactions = new HashSet<>();

    public AccountDTO(Account account) {
        Id = account.getId();
        number = account.getNumber();
        creationDate = account.getCreationDate();
        balance = account.getBalance();
        transactions = account.getTransactions().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toSet());
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
    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }
}
