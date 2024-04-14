package com.jpmc.todo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "orders")
public class OrderEntity implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String customerName;

    private LocalDate orderDate;

    @ManyToMany(mappedBy = "orders")
    private List<ProductEntity> products;
}
