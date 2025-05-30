package com.avikberg.poker.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stock {
    private static final String[] colors = {
        "h", "r", "k", "s" // hjerter, ruter, kløver, spar
    };
    private static final String[] values = {
        "a", "2", "3", "4", "5", "6", "7", "8", "9", "t", "j", "q", "k"
    };
    private static List<Card> getDefaultStock() {
        List<Card> stock = new ArrayList<>();
        for (String color : colors) {
            for (String value : values) {
                Card card = new Card();
                card.setValue(value);
                card.setColor(color);
                stock.add(card);
            }
        }
        return stock;
    }

    private long id;
    private final List<Card> cards;

    public Stock() {
        cards = Stock.getDefaultStock();
        Collections.shuffle(cards);
    }

    public List<Card> getCards() { return cards; }

}
