package com.enigma.wmb.service.impl;

import com.enigma.wmb.entity.Customer;
import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.model.*;
import com.enigma.wmb.repository.CustomerRepository;
import com.enigma.wmb.service.CustomerService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Page<Customer> getAll(SearchCustomerRequest request) {
        PageRequest pageable = PageRequest.of(0,5);
        Specification<Customer> customerSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName()!= null){
                Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%"+request.getName()+"%");
                predicates.add(namePredicate);
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
        return customerRepository.findAll(customerSpecification, pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerResponse getById(String id) {
        Optional<Customer> byId = customerRepository.findById(id);

        CustomerResponse response = CustomerResponse.builder()
                .id(byId.get().getId())
                .name(byId.get().getName())
                .phone(byId.get().getPhone_number())
                .build();

        return response;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer update(Customer request) {
        Optional<Customer> byId = customerRepository.findById(request.getId());

        Customer customer = Customer.builder()
                .id(byId.get().getId())
                .name(request.getName())
                .phone_number(request.getPhone_number())
                .user_id(request.getUser_id())
                .build();
        Customer save = customerRepository.save(customer);

        Customer response = Customer.builder()
                .id(save.getId())
                .name(save.getName())
                .user_id(save.getUser_id())
                .phone_number(save.getPhone_number())
                .build();
        return response;
    }

    @Override
    public String delete(String id) {
        customerRepository.deleteById(id);
        return "ok";
    }

    @Override
    public Customer findById(String id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) return optionalCustomer.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found");

    }
}
