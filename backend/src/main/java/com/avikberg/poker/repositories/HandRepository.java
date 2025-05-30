package com.avikberg.poker.repositories;

import com.avikberg.poker.models.Hand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandRepository extends JpaRepository<Hand, Long> {}
