package com.MindHub.homebanking.dto;

import com.MindHub.homebanking.models.Transaction;
import com.MindHub.homebanking.models.TransactionType;

import java.time.LocalDate;

public class TransactionDTO {
    private Long Id;
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDate date;

    public TransactionDTO(Transaction transaction) {
        Id = transaction.getId();
        type = transaction.getType();
        amount = transaction.getAmount();
        description = transaction.getDescription();
        date = transaction.getDate();
    }

    public Long getId() {
        return Id;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
}
