package com.enigma.wmb.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WebResponse<T> {
    private String status;
    private String message;
    private T data;

}
