package com.enigma.wmb.service.impl;

import com.enigma.wmb.constant.ERole;
import com.enigma.wmb.entity.Role;
import com.enigma.wmb.repository.RoleRepository;
import com.enigma.wmb.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role getOrSave(ERole eRole) {
        Optional<Role> optionalRole = roleRepository.findByRole(eRole);
        if (optionalRole.isPresent()) return optionalRole.get();

        // jika tidak kita simpan
        Role role = Role.builder()
                .role(eRole)
                .build();
        return roleRepository.save(role);
    }

}
