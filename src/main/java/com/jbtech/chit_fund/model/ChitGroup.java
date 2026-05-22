package com.jbtech.chit_fund.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChitGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupId;

    private Double premium;

    private Integer duration;

    private String chitGroupName;

    private Double sumAssured;

    private LocalDate startDate;

    private String isActive;

    @CreationTimestamp
    private LocalDate createdAt;

    @Version
    private Integer version;
}