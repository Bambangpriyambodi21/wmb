package com.enigma.wmb.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuResponse {

    private String id;
    private String name;
    private Long price;
}
