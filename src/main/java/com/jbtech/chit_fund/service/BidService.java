package com.jbtech.chit_fund.service;

import com.jbtech.chit_fund.model.Bid;

import java.util.List;
import java.util.Optional;

public interface BidService {
    Bid createBid(Bid bid);
    Optional<Bid> getBidById(Long id);
    List<Bid> getAllBids();
    void deleteBid(Long id);
}