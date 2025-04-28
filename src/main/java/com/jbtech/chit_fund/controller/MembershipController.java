package com.jbtech.chit_fund.controller;

import com.jbtech.chit_fund.dto.MembershipRequest;
import com.jbtech.chit_fund.exception.ResourceNotFoundException;
import com.jbtech.chit_fund.model.Membership;
import com.jbtech.chit_fund.service.MembershipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping
    public ResponseEntity<Membership> createMembership(@RequestBody MembershipRequest membershipRequest) {
        Membership createdMembership = membershipService.createMembership(membershipRequest);
        return new ResponseEntity<>(createdMembership, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membership> getMembershipById(@PathVariable Long id) {
        Membership membership = membershipService.getMembershipById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));
        return ResponseEntity.ok(membership);
    }

    @GetMapping
    public ResponseEntity<List<Membership>> getAllMemberships() {
        List<Membership> memberships = membershipService.getAllMemberships();
        return ResponseEntity.ok(memberships);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable Long id) {
        membershipService.deleteMembership(id);
        return ResponseEntity.noContent().build();
    }
}