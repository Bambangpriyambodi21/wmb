package com.enigma.wmb.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponse {
    private String id;
    private String name;
    private String phone;
}
