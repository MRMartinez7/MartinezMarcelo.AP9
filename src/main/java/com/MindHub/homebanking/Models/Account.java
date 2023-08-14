package com.MindHub.homebanking.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
@Id
@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
@GenericGenerator(name = "native", strategy = "native")
private long Id;
private String number;
private LocalDate creationDate;
private double balance;
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "owner_id")
private Client owner;

    @OneToMany(mappedBy="accountowner", fetch=FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();


    public void addTransaction(Transaction transaction) {
        transaction.setAccountowner(this);
        transactions.add(transaction);
    }


//constructor


    public Account() {
    }

    public Account(String number, LocalDate creationDate, double balance) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;

    }

    //Getter

    public long getId() {
        return Id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public Client getOwner() {
        return owner;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    //Setters
    public void setNumber(String number) {
        this.number = number;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }
}

