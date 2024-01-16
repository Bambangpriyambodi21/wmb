package com.enigma.wmb.service;

import com.enigma.wmb.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User loadByUserId(String userId);
//    UserDetails loadUserByUsername(String username);
}
