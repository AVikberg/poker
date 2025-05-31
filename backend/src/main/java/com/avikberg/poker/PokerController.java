package com.avikberg.poker;

import com.avikberg.poker.models.*;
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

    public PokerController(HandService handService) {
        this.handService = handService;
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

    @GetMapping("/analysis/{id}")
    public ResponseEntity<AnalysisDTO> analyseHandById(@PathVariable Long id) {
        Optional<AnalysisDTO> analysis = handService.getHandAnalysisById(id);
        return analysis.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/compare")
    public String compareHands(@RequestParam List<Long> ids) {
        List<Hand> hands = handService.getHandsByIds(ids);

        Hand best = hands.get(0);

        for (Hand hand : hands) {
            AnalysisDTO analysis = hand.findCategory();
            AnalysisDTO bestAnalysis = best.findCategory();
            if (analysis.category().getValue() > bestAnalysis.category().getValue()) {
                best = hand;
                continue;
            }
            if (analysis.category().getValue().equals(bestAnalysis.category().getValue())
                && analysis.highestInCategory() > bestAnalysis.highestInCategory()) {
                best = hand;
            }
        }

        return String.format(
            "Best hand being played was id %s (cards: %s, %s)",
            best.getId(), best.getCardsString(),
            best.findCategory().category().getName().toLowerCase()
        );
    }

    @PostMapping
    public AnalysisDTO createHand() {
        List<Card> stock = (new Stock()).getCards();

        List<Card> cardsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cardsList.add(stock.get(i));
        }

        Hand hand = new Hand();
        hand.setCards(cardsList);

        handService.saveHand(hand);
        return hand.findCategory();
    }

}
