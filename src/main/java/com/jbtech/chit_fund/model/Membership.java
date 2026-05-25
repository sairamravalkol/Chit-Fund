package com.jbtech.chit_fund.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id", nullable = false)
    private SubscriberEntity subscriber;
    private String subscriberName;
    private String premium;
    private String agentName;
    @CreationTimestamp
    private LocalDate joinedAt;
}