package com.jbtech.chit_fund.repository;

import com.jbtech.chit_fund.model.ChitGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChitGroupRepository extends JpaRepository<ChitGroup, Long> {
    // You can define custom query methods here if needed
}