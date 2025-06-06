package com.avikberg.poker.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;
    private String color;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int valueToInt() {
        return switch (value) {
            case "a" -> 1;
            case "t" -> 10;
            case "j" -> 11;
            case "q" -> 12;
            case "k" -> 13;
            default -> Integer.parseInt(value);
        };
    }

    @Override
    public String toString() {
        return value + color;
    }
}
