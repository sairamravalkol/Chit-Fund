package com.jbtech.chit_fund.repository;

import com.jbtech.chit_fund.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    // Additional query methods can be defined here if needed
}