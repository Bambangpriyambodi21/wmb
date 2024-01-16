package com.enigma.wmb.model;

import com.enigma.wmb.entity.Customer;
import com.enigma.wmb.entity.OrderDetail;
import com.enigma.wmb.entity.TableM;
import com.enigma.wmb.entity.TransType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private String id;
    private String customer_id;
    private String table_id;
    private String trans_type;
}
