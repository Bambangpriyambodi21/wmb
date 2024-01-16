package com.enigma.wmb.service.impl;

import com.enigma.wmb.constant.ERole;
import com.enigma.wmb.entity.Customer;
import com.enigma.wmb.entity.Role;
import com.enigma.wmb.entity.User;
import com.enigma.wmb.model.AuthRequest;
import com.enigma.wmb.model.UserResponse;
import com.enigma.wmb.repository.UserRepository;
import com.enigma.wmb.security.JwtUtils;
import com.enigma.wmb.service.AuthService;
import com.enigma.wmb.service.CustomerService;
import com.enigma.wmb.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final CustomerService customerService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
//    private final ValidationUtils validationUtils;

    private static UserResponse toUserResponse(User user) {
        List<String> roles = user.getRoles().stream().map(role -> role.getRole().name()).toList();
        return UserResponse.builder()
                .username(user.getUsername())
                .roles(roles)
                .build();
    }


    @PostConstruct
    @Transactional(rollbackFor = Exception.class)
    public void initSuperAdmin() {
        Optional<User> optionalUserCredential = userRepository
                .findByUsername("superadmin@gmail.com");

        if (optionalUserCredential.isPresent()) return;

        Role roleSuperAdmin = roleService.getOrSave(ERole.ROLE_SUPER_ADMIN);

        String hashPassword = passwordEncoder.encode("password");

        User userCredential = User.builder()
                .username("superadmin@gmail.com")
                .password(hashPassword)
                .roles(List.of(roleSuperAdmin))
                .build();
        userRepository.saveAndFlush(userCredential);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResponse register(AuthRequest request) {
        Role roleCustomer = roleService.getOrSave(ERole.ROLE_CUSTOMER);

        // hash password
        String hashPassword = passwordEncoder.encode(request.getPassword());

        User userCredential = User.builder()
                .username(request.getUsername())
                .password(hashPassword)
                .roles(List.of(roleCustomer))
                .build();

        userRepository.saveAndFlush(userCredential);

        Customer customer = Customer.builder()
                .name(request.getName())
                .phone_number(request.getPhone())
                .user_id(userCredential)
                .build();
        customerService.create(customer);

        return toUserResponse(userCredential);

    }

    @Override
    public UserResponse registerAdmin(AuthRequest request) {
        Role roleAdmin = roleService.getOrSave(ERole.ROLE_ADMIN);

        String hashPassword = passwordEncoder.encode(request.getPassword());

        User userCredential = User.builder()
                .username(request.getUsername())
                .password(hashPassword)
                .roles(List.of(roleAdmin))
                .build();
        userRepository.saveAndFlush(userCredential);

        return toUserResponse(userCredential);

    }

    @Override
    public String login(AuthRequest request) {

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        User user = (User) authenticate.getPrincipal();

        return jwtUtils.generateToken(user);

    }
}
