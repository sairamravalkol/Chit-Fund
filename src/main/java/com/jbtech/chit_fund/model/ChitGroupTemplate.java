package com.jbtech.chit_fund.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "chit_group_template")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChitGroupTemplate extends AuditColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_id", nullable = false, unique = true)
    private String groupId;

    @Column(nullable = false)
    private Double premiumMonthly;

    @Column(name = "chit_group_name", nullable = false)
    private String chitGroupName;

    @Column(name = "sum_assured", nullable = false)
    private Double sumAssured;

    @Column(name = "no_of_month", nullable = false)
    private Integer noOfMonth;

    @Version
    private Integer version;
}