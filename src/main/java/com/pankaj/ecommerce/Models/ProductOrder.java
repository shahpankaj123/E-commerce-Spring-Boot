package com.pankaj.ecommerce.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "ProductOrder")
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private int quantity;

    private BigDecimal totalPrice;

    private LocalDateTime orderDate;

    private String status;

    // Add any additional fields, like shipping details, payment info, etc.
}
