package com.enigma.wmb.model;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchOrderRequest {
    private String transType;
    private Long productName;
    private String hari;
    private String bulan;
    private String tahun;
    private Date startDate;
    private Date endDate;
}
