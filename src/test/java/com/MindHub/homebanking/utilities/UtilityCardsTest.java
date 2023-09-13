package com.MindHub.homebanking.utilities;

import com.MindHub.homebanking.models.Card;
import com.MindHub.homebanking.repositories.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)

class UtilityCardsTest {
@Autowired
private CardRepository cardRepository;
    @Test
    void cardNumberCreate() {
        String numberCard = UtilityCards.cardNumberCreate();
        List<String> cardNumbers = cardRepository.findAll().stream().map(Card::getNumber).collect(Collectors.toList());
        assertThat(numberCard,not(cardNumbers.contains(numberCard)));
    }

    @Test
    void createCVV() {
        String cvv = String.valueOf(UtilityCards.createCVV());
        assertThat(cvv,is(not(emptyOrNullString())));
    }
}