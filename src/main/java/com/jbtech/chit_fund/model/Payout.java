package com.jbtech.chit_fund.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payouts")
public class Payout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long chitGroupId;

    @Column(nullable = false)
    private String month;

    @Column(nullable = false)
    private BigDecimal amount;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChitGroupId() {
        return chitGroupId;
    }

    public void setChitGroupId(Long chitGroupId) {
        this.chitGroupId = chitGroupId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}