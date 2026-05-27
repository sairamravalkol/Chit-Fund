package com.jbtech.chit_fund.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "membership")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Membership extends AuditColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id", nullable = false)
    private SubscriberEntity subscriber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chit_group_id", nullable = false)
    private ChitGroup chitGroup;

    @Column(name = "subscriber_name")
    private String subscriberName;

    @Column(name = "premium")
    private String premium;

    @Column(name = "agent_name")
    private String agentName;

    @Column(name = "joined_at")
    private LocalDate joinedAt;
}