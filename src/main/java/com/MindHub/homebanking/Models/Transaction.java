package com.MindHub.homebanking.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long Id;
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account accountowner;


    //construtor
    public Transaction() {
    }

    public Transaction(TransactionType type, double amount, String description, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;

    }
    //Getters

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

    public Account getAccountowner() {
        return accountowner;
    }
    //Setters

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAccountowner(Account accountowner) {
        this.accountowner = accountowner;
    }
}
