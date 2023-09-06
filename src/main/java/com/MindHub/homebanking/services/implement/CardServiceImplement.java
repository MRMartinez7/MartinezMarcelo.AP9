package com.MindHub.homebanking.services.implement;

import com.MindHub.homebanking.dto.CardDTO;
import com.MindHub.homebanking.models.Card;
import com.MindHub.homebanking.repositories.CardRepository;
import com.MindHub.homebanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImplement implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card findById(long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public List<CardDTO> getCards() {
        return cardRepository.findAll().stream().map(CardDTO::new).collect(Collectors.toList());
    }

    @Override
    public Boolean existsByNumber(String number) {
        return cardRepository.existsByNumber(number);
    }

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }
}
