package com.avikberg.poker.repositories;

import com.avikberg.poker.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {}
