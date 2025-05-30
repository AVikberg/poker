package com.avikberg.poker;

import com.avikberg.poker.models.*;
import com.avikberg.poker.services.CardService;
import com.avikberg.poker.services.HandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hands")
public class PokerController {
    private final HandService handService;
    private final CardService cardService;

    public PokerController(HandService handService, CardService cardService) {
        this.handService = handService;
        this.cardService = cardService;
    }


    @GetMapping
    public List<HandDTO> getAllHands() {
        return handService.getAllHands();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HandDTO> getHandById(@PathVariable Long id) {
        Optional<HandDTO> hand = handService.getHandById(id);
        return hand.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public HandDTO createHand() {
        List<Card> stock = (new Stock()).getCards();

        List<Card> cardsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cardsList.add(stock.get(i));
        }

        Hand hand = new Hand();
        hand.setCards(cardsList);

        return handService.saveHand(hand);
    }

}
