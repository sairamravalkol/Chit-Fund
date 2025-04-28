package com.jbtech.chit_fund.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "chit_group_id", nullable = false)
    private ChitGroup chitGroup;

    @CreationTimestamp
    private LocalDateTime joinedAt;
}