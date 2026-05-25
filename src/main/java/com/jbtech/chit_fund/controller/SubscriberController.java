package com.jbtech.chit_fund.controller;

import com.jbtech.chit_fund.dto.MembershipRequest;
import com.jbtech.chit_fund.dto.SubscriberDTO;
import com.jbtech.chit_fund.exception.ResourceNotFoundException;
import com.jbtech.chit_fund.model.Membership;
import com.jbtech.chit_fund.model.SubscriberEntity;
import com.jbtech.chit_fund.service.MembershipService;
import com.jbtech.chit_fund.service.SubscriberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {

    private final SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping
    public ResponseEntity<String> createSubscriber(@RequestBody SubscriberDTO subscriberDTO) {
        String response = subscriberService.createSubscriber(subscriberDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriberEntity> getSubscriberById(@PathVariable Long id) {
        SubscriberEntity subscriber = subscriberService.getSubscriberById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscriber not found with id: " + id));
        return ResponseEntity.ok(subscriber);
    }

    @GetMapping
    public ResponseEntity<List<SubscriberDTO>> getAllSubscribers() {
        List<SubscriberDTO> subscribers = subscriberService.getAllSubscribers();
        return ResponseEntity.ok(subscribers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscriber(@PathVariable Long id) {
        subscriberService.deleteSubscriber(id);
        return ResponseEntity.noContent().build();
    }
}