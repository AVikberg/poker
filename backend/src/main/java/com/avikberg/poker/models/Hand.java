package com.avikberg.poker.models;

import com.avikberg.poker.CardCategory;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Entity
public class Hand {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "hand_id")
    @Cascade({CascadeType.ALL})
    private List<Card> cards;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<Card> getCards() { return cards; }
    public void setCards(List<Card> cards) { this.cards = cards; }


    public String getCardsString() {
        List<String> cardStrings = cards.stream().map(Card::toString).toList();
        return String.join(", ", cardStrings);
    }


    private Map<String, List<Card>> groupByValue() {
        return cards.stream().collect(groupingBy(Card::getValue));
    }

    public AnalysisDTO findCategory() {
        int pairs = 0;
        int threeOfAKind = 0;
        int fourOfAKind = 0;

        int highestCard = 0;
        int highestPair = 0;
        int highestThreeOfAKind = 0;
        int highestFourOfAKind = 0;
        int highestInCategory = 0;

        for (Map.Entry<String, List<Card>> kv : groupByValue().entrySet()) {
            if (kv.getValue().size() == 2) {
                pairs++;
                for (Card card : kv.getValue()) {
                    int cardValue = card.valueToInt();
                    if (cardValue > highestPair) {
                        highestPair = cardValue;
                    }
                }
                continue;
            }
            if (kv.getValue().size() == 3) {
                threeOfAKind++;
                for (Card card : kv.getValue()) {
                    int cardValue = card.valueToInt();
                    if (cardValue > highestThreeOfAKind) {
                        highestThreeOfAKind = cardValue;
                    }
                }
                continue;
            }
            if (kv.getValue().size() == 4) {
                fourOfAKind++;
                for (Card card : kv.getValue()) {
                    int cardValue = card.valueToInt();
                    if (cardValue > highestFourOfAKind) {
                        highestFourOfAKind = cardValue;
                    }
                }
                continue;
            }

            int cardValue = kv.getValue().get(0).valueToInt();
            if (cardValue > highestCard) {
                highestCard = cardValue;
            }
        }

        CardCategory category;

        if (fourOfAKind > 0) {
            category = CardCategory.FourOfAKind;
            highestInCategory = highestFourOfAKind;
        } else if (threeOfAKind > 0 && pairs > 0) {
            category = CardCategory.FullHouse;
            highestInCategory = highestThreeOfAKind;
        } else if (threeOfAKind > 0) {
            category = CardCategory.ThreeOfAKind;
            highestInCategory = highestThreeOfAKind;
        } else if (pairs == 2) {
            category = CardCategory.TwoPair;
            highestInCategory = highestPair;
        } else if (pairs == 1) {
            category = CardCategory.OnePair;
            highestInCategory = highestPair;
        } else {
            category = CardCategory.HighCard;
            highestInCategory = highestCard;
        }

        return new AnalysisDTO(
            id,
            cards,
            category,
            highestInCategory
        );
    }

}
