package com.enigma.wmb.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthRequest {
    @Email(message = "invalid email")
    private String username;
    @Size(min = 6, message = "password minimum 6 character")
    private String password;
    private String name;
    private String phone;
}

