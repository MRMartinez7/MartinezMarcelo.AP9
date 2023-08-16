package com.MindHub.homebanking.dto;

import com.MindHub.homebanking.Models.Card;
import com.MindHub.homebanking.Models.CardColor;
import com.MindHub.homebanking.Models.CardType;

import java.time.LocalDate;

public class CardDTO {
    private long id;
    private String cardHolder;
    private CardType type;
    private CardColor color;
    private String number;
    private int CVV;
    private LocalDate thruDate;
    private LocalDate fromDate;


    //constructor

    public CardDTO(Card card) {
        id = card.getId();
        cardHolder = card.getCardHolder();
        type = card.getType();
        color = card.getColor();
        number = card.getNumber();
        CVV = card.getCVV();
        thruDate = card.getThruDate();
        fromDate =card.getFromDate();
    }


    //Getter

    public long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public CardType getType() {
        return type;
    }

    public CardColor getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public int getCVV() {
        return CVV;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

}
