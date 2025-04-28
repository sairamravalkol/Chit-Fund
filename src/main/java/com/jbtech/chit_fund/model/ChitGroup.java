package com.jbtech.chit_fund.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Data
public class ChitGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double amount;

    private Integer duration;

    private LocalDate startDate;

    private String isActive;

    @CreationTimestamp
    private LocalDate createdAt;

    @Version
    private Integer version;
}