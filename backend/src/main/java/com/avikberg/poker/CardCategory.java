package com.avikberg.poker;

public enum CardCategory {
    HighCard(0, "High card"),
    OnePair(1, "One pair"),
    TwoPair(2, "Two pairs"),
    ThreeOfAKind(3, "Three of a kind"),
    FullHouse(4, "Full house"),
    FourOfAKind(5, "Four of a kind");

    private final Integer value;
    private final String name;

    CardCategory(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
    public Integer getValue() { return value; }
    public String getName() { return name; }
}
