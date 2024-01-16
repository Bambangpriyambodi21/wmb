package com.enigma.wmb.service;

import com.enigma.wmb.entity.TransType;
import com.enigma.wmb.repository.TransTypeRepository;

import java.util.Optional;

public interface TransTypeService {
    TransType getById(String id);
    TransType getByTransType(String transType);
}
