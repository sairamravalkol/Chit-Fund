package com.jbtech.chit_fund.service;

import com.jbtech.chit_fund.dto.MembershipRequest;
import com.jbtech.chit_fund.model.Membership;

import java.util.List;
import java.util.Optional;

public interface MembershipService {
    Membership createMembership(MembershipRequest membershipRequest);
    Optional<Membership> getMembershipById(Long id);
    List<Membership> getAllMemberships();
    void deleteMembership(Long id);
}