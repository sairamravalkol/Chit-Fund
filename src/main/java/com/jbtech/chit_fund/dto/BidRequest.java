package com.jbtech.chit_fund.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BidRequest {
    private Long userId;
    private Long chitGroupId;
    private BigDecimal amount;
    private LocalDateTime bidDate;
    private Boolean isWinner;

    // Getters and Setters
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getBidDate() {
        return bidDate;
    }

    public void setBidDate(LocalDateTime bidDate) {
        this.bidDate = bidDate;
    }

    public Boolean getIsWinner() {
        return isWinner;
    }

    public void setIsWinner(Boolean isWinner) {
        this.isWinner = isWinner;
    }
}