package com.enigma.wmb.service;

import com.enigma.wmb.model.AuthRequest;
import com.enigma.wmb.model.UserResponse;

public interface AuthService {
    UserResponse register(AuthRequest request);
    UserResponse registerAdmin(AuthRequest request);
    String login(AuthRequest request);

}
