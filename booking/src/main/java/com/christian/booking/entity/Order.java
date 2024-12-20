package com.christian.booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "order")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_no")
    private String orderNo;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
