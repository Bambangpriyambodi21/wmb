package com.enigma.wmb.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SesrchTableRequest {
    private String name;
    private Long minPrice;
    private Long maxPrice;
}
