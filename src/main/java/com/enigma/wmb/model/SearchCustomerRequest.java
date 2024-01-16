package com.enigma.wmb.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchCustomerRequest {
    private String name;
}
