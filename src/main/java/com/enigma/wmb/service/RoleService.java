package com.enigma.wmb.service;

import com.enigma.wmb.constant.ERole;
import com.enigma.wmb.entity.Role;

public interface RoleService {
    Role getOrSave(ERole role);
}
