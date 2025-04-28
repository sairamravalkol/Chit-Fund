package com.jbtech.chit_fund.dto;

import java.time.LocalDateTime;

public class MembershipRequest {
    private Long userId;
    private Long chitGroupId;
    private LocalDateTime joinedAt;

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

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}