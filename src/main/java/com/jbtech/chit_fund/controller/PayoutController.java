package com.jbtech.chit_fund.controller;

import com.jbtech.chit_fund.model.Payout;
import com.jbtech.chit_fund.service.PayoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payouts")
public class PayoutController {

    private final PayoutService payoutService;

    public PayoutController(PayoutService payoutService) {
        this.payoutService = payoutService;
    }

    @PostMapping
    public ResponseEntity<Payout> createPayout(@RequestBody Payout payout) {
        Payout createdPayout = payoutService.createPayout(payout);
        return new ResponseEntity<>(createdPayout, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payout> getPayoutById(@PathVariable Long id) {
        Payout payout = payoutService.getPayoutById(id)
                .orElseThrow(() -> new RuntimeException("Payout not found with id: " + id));
        return ResponseEntity.ok(payout);
    }

    @GetMapping
    public ResponseEntity<List<Payout>> getAllPayouts() {
        List<Payout> payouts = payoutService.getAllPayouts();
        return ResponseEntity.ok(payouts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayout(@PathVariable Long id) {
        payoutService.deletePayout(id);
        return ResponseEntity.noContent().build();
    }
}