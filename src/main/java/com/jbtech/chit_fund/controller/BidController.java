package com.jbtech.chit_fund.controller;

import com.jbtech.chit_fund.dto.BidRequest;
import com.jbtech.chit_fund.exception.ResourceNotFoundException;
import com.jbtech.chit_fund.model.Bid;
import com.jbtech.chit_fund.model.ChitGroup;
import com.jbtech.chit_fund.repository.ChitGroupRepository;
import com.jbtech.chit_fund.service.BidService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bids")
public class BidController {

    private final BidService bidService;
    private final ChitGroupRepository chitGroupRepository;

    public BidController(BidService bidService, ChitGroupRepository chitGroupRepository) {
        this.chitGroupRepository = chitGroupRepository;
        this.bidService = bidService;
    }

    @PostMapping
    public ResponseEntity<Bid> createBid(@RequestBody BidRequest bidRequest) {
        Bid bid = new Bid();
        bid.setUserId(bidRequest.getUserId());
        bid.setAmount(bidRequest.getAmount());
        bid.setBidDate(bidRequest.getBidDate());
        bid.setIsWinner(bidRequest.getIsWinner());

        ChitGroup chitGroup = chitGroupRepository.findById(bidRequest.getChitGroupId())
                .orElseThrow(() -> new ResourceNotFoundException("ChitGroup not found with id: " + bidRequest.getChitGroupId()));
        bid.setChitGroup(chitGroup);

        Bid createdBid = bidService.createBid(bid);
        return new ResponseEntity<>(createdBid, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bid> getBidById(@PathVariable Long id) {
        Bid bid = bidService.getBidById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found with id: " + id));
        return ResponseEntity.ok(bid);
    }

    @GetMapping
    public ResponseEntity<List<Bid>> getAllBids() {
        List<Bid> bids = bidService.getAllBids();
        return ResponseEntity.ok(bids);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBid(@PathVariable Long id) {
        bidService.deleteBid(id);
        return ResponseEntity.noContent().build();
    }
}