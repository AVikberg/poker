package com.avikberg.poker.repositories;

import com.avikberg.poker.models.Hand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HandRepository extends JpaRepository<Hand, Long> {
    List<Hand> findByIdIn(List<Long> ids);
}
