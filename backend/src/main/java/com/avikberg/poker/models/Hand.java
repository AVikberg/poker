package com.avikberg.poker.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

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
}
