package com.jbtech.chit_fund.repository;

import com.jbtech.chit_fund.model.Payout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayoutRepository extends JpaRepository<Payout, Long> {
    // You can define custom query methods here if needed
}