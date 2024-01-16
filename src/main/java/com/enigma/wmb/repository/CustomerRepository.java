package com.enigma.wmb.repository;

import com.enigma.wmb.entity.Customer;
import com.enigma.wmb.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {

    Page<Customer> findAllByName(Pageable pageable, String name);

}
