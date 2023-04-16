package com.cabalak.backendchallenge.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order extends BaseEntity {

    private LocalDateTime createDate;
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    public Order(LocalDateTime createDate, BigDecimal totalPrice, Customer customer) {
        this.createDate = createDate;
        this.totalPrice = totalPrice;
        this.customer = customer;
    }
}
