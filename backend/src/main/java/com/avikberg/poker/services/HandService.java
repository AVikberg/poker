package com.avikberg.poker.services;

import com.avikberg.poker.models.Hand;
import com.avikberg.poker.models.HandDTO;

import java.util.List;
import java.util.Optional;

public interface HandService {
    List<HandDTO> getAllHands();
    Optional<HandDTO> getHandById(Long id);
    HandDTO saveHand(Hand hand);
    HandDTO saveHand(HandDTO handDTO);
    HandDTO updateHand(Long id, HandDTO handDTO);
    void deleteHand(Long id);
}
