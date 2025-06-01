package com.avikberg.poker.models;

import java.util.List;

import com.avikberg.poker.CardCategory;

public record AnalysisDTO(Long handId, List<Card> cards, CardCategory category, int highestInCategory) {}
