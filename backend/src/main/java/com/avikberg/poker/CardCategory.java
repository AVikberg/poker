package com.avikberg.poker;

public enum CardCategory {
    HighCard(0, "High card"),
    OnePair(1, "One pair"),
    TwoPair(2, "Two pairs"),
    ThreeOfAKind(3, "Three of a kind"),
    Flush(4, "Flush"),
    FullHouse(5, "Full house"),
    FourOfAKind(6, "Four of a kind"),
    StraightFlush(7, "Straight Flush");

    private final Integer value;
    private final String name;

    CardCategory(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
    public Integer getValue() { return value; }
    public String getName() { return name; }
}
