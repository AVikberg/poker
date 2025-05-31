package com.avikberg.poker.models;

import com.avikberg.poker.CardCategory;

public record AnalysisDTO(Long cardId, String cards, CardCategory category, int highestInCategory) {}
