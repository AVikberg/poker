package com.avikberg.poker.services;

import com.avikberg.poker.models.AnalysisDTO;
import com.avikberg.poker.models.Card;
import com.avikberg.poker.models.Hand;
import com.avikberg.poker.models.HandDTO;
import com.avikberg.poker.repositories.HandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HandServiceImpl implements HandService {
    private final HandRepository handRepository;

    public HandServiceImpl(HandRepository handRepository) {
        this.handRepository = handRepository;
    }

    @Override
    public List<HandDTO> getAllHands() {
        return handRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<HandDTO> getHandById(Long id) {
        return handRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public Optional<AnalysisDTO> getHandAnalysisById(Long id) {
        Optional<Hand> hand = handRepository.findById(id);
        return hand.map(value -> Optional.ofNullable(value.findCategory())).orElse(null);
    }

    @Override
    public HandDTO saveHand(Hand hand) {
        Hand savedHand = handRepository.save(hand);
        return convertToDTO(savedHand);
    }

    @Override
    public HandDTO saveHand(HandDTO handDTO) {
        Hand hand = convertToEntity(handDTO);
        Hand savedHand = handRepository.save(hand);
        return convertToDTO(savedHand);
    }

    @Override
    public HandDTO updateHand(Long id, HandDTO handDTO) {
        Hand hand = handRepository.findById(id).orElseThrow();
        hand.setCards(handDTO.cards());
        Hand updatedHand = handRepository.save(hand);
        return convertToDTO(updatedHand);
    }

    @Override
    public void deleteHand(Long id) {
        handRepository.deleteById(id);
    }


    private HandDTO convertToDTO(Hand hand) {
        List<Card> cards = hand.getCards();
        return new HandDTO(hand.getId(), cards);
    }

    private Hand convertToEntity(HandDTO handDTO) {
        Hand hand = new Hand();
        hand.setCards(handDTO.cards());
        return hand;
    }
}
