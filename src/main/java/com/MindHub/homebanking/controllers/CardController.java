package com.MindHub.homebanking.controllers;


import com.MindHub.homebanking.dto.AccountDTO;
import com.MindHub.homebanking.dto.CardDTO;
import com.MindHub.homebanking.models.*;
import com.MindHub.homebanking.repositories.CardRepository;
import com.MindHub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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


    @GetMapping("/clients/current/cards")
    public List<CardDTO> getCurrentCards(Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName());
        return client.getCards().stream().map(CardDTO::new).collect(Collectors.toList());
    }

@Autowired
public ClientRepository clientRepository;

    @PostMapping(path = "/clients/current/cards")
    public ResponseEntity<Object> registerCard(Authentication authentication,
                                               @RequestParam CardType cardType,
                                               @RequestParam CardColor cardColor) {
        Client client = clientRepository.findByEmail(authentication.getName());
        List<CardType> cardTypes = client.getCards().stream().map(Card::getType).collect(Collectors.toList());
        int credit = 0;
        int debit = 0;
        for (CardType type: cardTypes ){
            if(type.equals(CardType.CREDIT)){
                credit++;
            }else{
                debit++;
            }
        }
        boolean color = false;
        for (Card card:client.getCards()
        ) {
            if (card.getType().equals(cardType) && card.getColor().equals(cardColor)){
                color = true;
            }
        }
            if((cardType.equals(CardType.CREDIT) && credit < 3 && !color) || (cardType.equals(CardType.DEBIT) && debit < 3 && !color))
{

                String numberNewCard;
                int numCVV;
                numCVV = Card.createCVV();
                do {
                    numberNewCard = Card.cardNumberCreate();
                }while (cardRepository.existsByNumber(numberNewCard));

                Card newCard = new Card(client.getFirstName() +" "+ client.getLastName(), cardType, cardColor ,numberNewCard , numCVV ,LocalDate.now().plusYears(5),LocalDate.now());
                cardRepository.save(newCard);
                client.addCard(newCard);
                clientRepository.save(client);
                return new ResponseEntity<>("Card create", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("This client has 3 card type equals ", HttpStatus.FORBIDDEN);
        }
        }

    }

