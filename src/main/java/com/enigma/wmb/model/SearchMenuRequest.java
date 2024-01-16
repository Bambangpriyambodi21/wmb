package com.enigma.wmb.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchMenuRequest {
    private String name;
    private Long minPrice;
    private Long maxPrice;
}
