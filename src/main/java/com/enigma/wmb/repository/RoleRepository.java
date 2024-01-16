package com.enigma.wmb.repository;

import com.enigma.wmb.constant.ERole;
import com.enigma.wmb.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(ERole role);
}
