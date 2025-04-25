package com.jbtech.chit_fund.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role; // e.g., "admin", "user"
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String isActive; // "Y" for active, "N" for inactive
    private String createdBy;

    @CreationTimestamp
    private String createdAt;

    @Version
    private Integer version;
   }