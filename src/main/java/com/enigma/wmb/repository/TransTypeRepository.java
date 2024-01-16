package com.enigma.wmb.repository;

import com.enigma.wmb.entity.TransType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransTypeRepository extends JpaRepository<TransType, String> {
    Optional<TransType> getAllByType(String transType);
}
