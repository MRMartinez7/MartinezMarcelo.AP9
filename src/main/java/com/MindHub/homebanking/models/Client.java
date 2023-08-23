package com.MindHub.homebanking.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String firstName;
    private  String lastName;
    private String email;
    private String password;
    private RolType rol;
    @OneToMany(mappedBy="owner", fetch= FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();


    public void addAccount(Account account) {
        account.setOwner(this);
        accounts.add(account);
    }
    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    @OneToMany(mappedBy="card", fetch=FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();
    public void  addCard(Card card){
        card.setCard(this);
        cards.add(card);
    }

     //Constructor
    public Client() {
    }

    public Client(String firstName, String lastName, String email,String password,RolType rol) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    //Getters

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public Set<ClientLoan> getLoans() {
        return clientLoans;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public RolType getRol() {
        return rol;
    }

    //Setters


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(RolType rol) {
        this.rol = rol;
    }
}
