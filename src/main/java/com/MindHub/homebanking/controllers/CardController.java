package com.MindHub.homebanking.controllers;


import com.MindHub.homebanking.dto.CardDTO;
import com.MindHub.homebanking.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/cards")
    public List<CardDTO> getCards(){
        return cardRepository.findAll().stream().map(CardDTO::new).collect(Collectors.toList());
    }
    @RequestMapping("/cards/{id}")
    public CardDTO getCardsById(@PathVariable Long id){
        return new CardDTO(cardRepository.findById(id).orElse(null));
    }
}
