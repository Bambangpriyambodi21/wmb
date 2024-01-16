package com.enigma.wmb.service;

import com.enigma.wmb.entity.Customer;
import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.model.CustomerRequest;
import com.enigma.wmb.model.CustomerResponse;
import com.enigma.wmb.model.SearchCustomerRequest;
import com.enigma.wmb.model.SearchMenuRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Page<Customer> getAll(SearchCustomerRequest searchCustomerRequest);
    CustomerResponse getById(String id);
    Customer update(Customer customer);
    String delete(String id);
    Customer findById(String id);
}
