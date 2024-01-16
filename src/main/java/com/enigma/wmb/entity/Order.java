package com.enigma.wmb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "m_order")
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JoinColumn(name = "customer_id", nullable = false)
    @ManyToOne
    @JsonBackReference
    private Customer customer_id;

    @JoinColumn(name = "table_id", nullable = false)
    @ManyToOne
    @JsonBackReference
    private TableM table_id;

    @JoinColumn(name = "trans_type_id", nullable = false)
    @ManyToOne
    @JsonBackReference
    private TransType trans_type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date trans_date;


}
