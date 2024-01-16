package com.enigma.wmb.controller;

import com.enigma.wmb.entity.Customer;
import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.model.*;
import com.enigma.wmb.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(path = "/api/customers")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String name) {
        SearchCustomerRequest searchCustomerRequest = SearchCustomerRequest.builder()
                .name(name)
                .build();
        Page<Customer> allMenu = customerService.getAll(searchCustomerRequest);
        WebResponse<Page<Customer>> response = WebResponse.<Page<Customer>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("get all customer")
                .data(allMenu)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(path = "/api/customers/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> getById(@PathVariable String id) {
        CustomerResponse byId = customerService.getById(id);
        WebResponse<CustomerResponse> response = WebResponse.<CustomerResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("get id customer")
                .data(byId)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping(path = "/api/customers")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> update(@RequestBody Customer customer){
        Customer customer1 = customerService.update(customer);
        WebResponse<Customer> response = WebResponse.<Customer>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("update new customer")
                .data(customer1)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping(path = "/api/customers/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        String menu = customerService.delete(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("delete id")
                .data("ok")
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
