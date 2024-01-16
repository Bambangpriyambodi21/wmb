package com.enigma.wmb.service.impl;

import com.enigma.wmb.entity.TransType;
import com.enigma.wmb.repository.TransTypeRepository;
import com.enigma.wmb.service.TransTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TransTypeServiceImpl implements TransTypeService {

    private final TransTypeRepository transTypeRepository;

    @Override
    public TransType getById(String id) {
        Optional<TransType> optionalCustomer = transTypeRepository.findById(id);
        if (optionalCustomer.isPresent()) return optionalCustomer.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found");

    }

    @Override
    public TransType getByTransType(String transType) {
        Optional<TransType> byTypeTrans = transTypeRepository.getAllByType(transType);
        if (byTypeTrans.isPresent()) return byTypeTrans.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found");

    }
}
