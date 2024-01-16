package com.enigma.wmb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "m_order_detail")
@Builder
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JoinColumn(name = "order_id")
    @ManyToOne
    @JsonBackReference
    private Order order_id;

    @JoinColumn(name = "menu_id")
    @ManyToOne
    @JsonBackReference
    private Menu menu_id;
    private Long price;
}
