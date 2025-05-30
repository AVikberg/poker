package com.avikberg.poker.services;

import com.avikberg.poker.models.Card;
import com.avikberg.poker.models.CardDTO;

import java.util.List;
import java.util.Optional;

public interface CardService {
    List<CardDTO> getAllCards();
    Optional<CardDTO> getCardById(Long id);
    CardDTO saveCard(Card card);
    CardDTO saveCard(CardDTO cardDTO);
    CardDTO updateCard(Long id, CardDTO cardDTO);
    void deleteCard(Long id);
}
