package com.jbtech.chit_fund.service;

import com.jbtech.chit_fund.dto.MembershipRequest;
import com.jbtech.chit_fund.exception.ResourceNotFoundException;
import com.jbtech.chit_fund.model.ChitGroup;
import com.jbtech.chit_fund.model.Membership;
import com.jbtech.chit_fund.repository.ChitGroupRepository;
import com.jbtech.chit_fund.repository.MembershipRepository;
import com.jbtech.chit_fund.service.MembershipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipServiceImpl implements MembershipService {

    private static final Logger logger = LoggerFactory.getLogger(MembershipServiceImpl.class);

    private final MembershipRepository membershipRepository;
    private final ChitGroupRepository chitGroupRepository;

    public MembershipServiceImpl(MembershipRepository membershipRepository, ChitGroupRepository chitGroupRepository) {
        this.membershipRepository = membershipRepository;
        this.chitGroupRepository = chitGroupRepository;
    }



    @Override
    public Membership createMembership(MembershipRequest membershipRequest) {
        ChitGroup chitGroup = chitGroupRepository.findById(membershipRequest.getChitGroupId())
                .orElseThrow(() -> new ResourceNotFoundException("ChitGroup not found with id: " + membershipRequest.getChitGroupId()));

        Membership membership = new Membership();
        membership.setUserId(membershipRequest.getUserId());
        membership.setChitGroup(chitGroup);
        membership.setJoinedAt(membershipRequest.getJoinedAt());

        logger.info("Creating a new Membership: {}", membership);
        return membershipRepository.save(membership);
    }

    @Override
    public Optional<Membership> getMembershipById(Long id) {
        logger.info("Fetching Membership with id: {}", id);
        return Optional.ofNullable(membershipRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Membership not found with id: {}", id);
                    return new ResourceNotFoundException("Membership not found with id: " + id);
                }));
    }

    @Override
    public List<Membership> getAllMemberships() {
        logger.info("Fetching all Memberships");
        return membershipRepository.findAll();
    }

    @Override
    public void deleteMembership(Long id) {
        logger.info("Deleting Membership with id: {}", id);
        if (!membershipRepository.existsById(id)) {
            logger.error("Membership not found with id: {}", id);
            throw new ResourceNotFoundException("Membership not found with id: " + id);
        }
        membershipRepository.deleteById(id);
        logger.info("Deleted Membership with id: {}", id);
    }
}