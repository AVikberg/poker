package com.avikberg.poker.services;

import com.avikberg.poker.models.Card;
import com.avikberg.poker.models.CardDTO;
import com.avikberg.poker.models.Hand;
import com.avikberg.poker.repositories.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<CardDTO> getAllCards() {
        return cardRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<CardDTO> getCardById(Long id) {
        return cardRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public CardDTO saveCard(Card card) {
        Card savedCard = cardRepository.save(card);
        return convertToDTO(savedCard);
    }

    @Override
    public CardDTO saveCard(CardDTO cardDTO)
    {
        Card card = convertToEntity(cardDTO);
        Card savedCard = cardRepository.save(card);
        return convertToDTO(savedCard);
    }

    @Override
    public CardDTO updateCard(Long id, CardDTO cardDTO) {
        Card card = cardRepository.findById(id).orElseThrow();
        card.setValue(cardDTO.value());
        card.setColor(cardDTO.color());
        Card updatedCard = cardRepository.save(card);
        return convertToDTO(updatedCard);
    }

    @Override
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }




    private CardDTO convertToDTO(Card card) {
        return new CardDTO(card.getId(), card.getValue(), card.getColor());
    }

    private Card convertToEntity(CardDTO cardDTO) {
        Card card = new Card();
        card.setValue(cardDTO.value());
        card.setColor(cardDTO.color());
        return card;
    }
}
