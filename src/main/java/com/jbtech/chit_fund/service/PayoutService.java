package com.jbtech.chit_fund.service;

import com.jbtech.chit_fund.model.Payout;

import java.util.List;
import java.util.Optional;

public interface PayoutService {
    Payout createPayout(Payout payout);
    Optional<Payout> getPayoutById(Long id);
    List<Payout> getAllPayouts();
    void deletePayout(Long id);
}