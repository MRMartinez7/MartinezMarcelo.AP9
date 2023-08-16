package com.MindHub.homebanking.Controller;

import com.MindHub.homebanking.Models.Card;
import com.MindHub.homebanking.Models.Client;
import com.MindHub.homebanking.Repository.CardRepository;
import com.MindHub.homebanking.dto.CardDTO;
import com.MindHub.homebanking.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/cards")
    public List<CardDTO> getCards(){
        List<Card> allCards = cardRepository.findAll();
        List<CardDTO> convertedListCards = allCards.stream().map(currentCard -> new CardDTO(currentCard)).collect(Collectors.toList());
        return convertedListCards;
    }
    @GetMapping("/cards/{id}")
    public CardDTO getCardById(@PathVariable long id){
        Optional<Card> card = cardRepository.findById(id);
        return new CardDTO(card.get());
    }
}
