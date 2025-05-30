package com.avikberg.poker.models;

import java.util.List;

public record HandDTO(Long id, List<Card> cards) {}
