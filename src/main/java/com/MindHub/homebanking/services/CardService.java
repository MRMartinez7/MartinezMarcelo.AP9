package com.MindHub.homebanking.services;

import com.MindHub.homebanking.dto.CardDTO;
import com.MindHub.homebanking.models.Card;

import java.util.List;

public interface CardService {
    Card findById(long id);
    List<CardDTO> getCards();
    Boolean existsByNumber(String number);
    void  save(Card card);

}
