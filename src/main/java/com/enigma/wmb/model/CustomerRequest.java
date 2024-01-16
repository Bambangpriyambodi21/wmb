package com.enigma.wmb.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequest {
    private String id;
    private String name;
    private String phone;
    private String user_id;

}
