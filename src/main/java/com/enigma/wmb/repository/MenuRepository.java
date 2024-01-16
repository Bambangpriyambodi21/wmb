package com.enigma.wmb.repository;

import com.enigma.wmb.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {
    Page<Menu> findAllByName(Pageable pageable, String name, Integer price);
}
