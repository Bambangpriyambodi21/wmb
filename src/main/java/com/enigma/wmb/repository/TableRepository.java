package com.enigma.wmb.repository;

import com.enigma.wmb.entity.TableM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TableRepository extends JpaRepository<TableM, String>, JpaSpecificationExecutor<TableM> {
}
