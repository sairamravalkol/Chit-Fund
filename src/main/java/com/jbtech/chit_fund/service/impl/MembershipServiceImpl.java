package com.jbtech.chit_fund.service.impl;

import com.jbtech.chit_fund.dto.MembershipRequest;
import com.jbtech.chit_fund.exception.DublicateRecordException;
import com.jbtech.chit_fund.exception.ResourceNotFoundException;
import com.jbtech.chit_fund.model.ChitGroup;
import com.jbtech.chit_fund.model.Membership;
import com.jbtech.chit_fund.model.SubscriberEntity;
import com.jbtech.chit_fund.repository.ChitGroupRepository;
import com.jbtech.chit_fund.repository.MembershipRepository;
import com.jbtech.chit_fund.repository.SubscriberRepository;
import com.jbtech.chit_fund.service.MembershipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MembershipServiceImpl implements MembershipService {

    private static final Logger logger = LoggerFactory.getLogger(MembershipServiceImpl.class);

    private final MembershipRepository membershipRepository;
    private final ChitGroupRepository chitGroupRepository;
    private final SubscriberRepository subscriberRepository;

    public MembershipServiceImpl(MembershipRepository membershipRepository, ChitGroupRepository chitGroupRepository, SubscriberRepository subscriberRepository) {
        this.membershipRepository = membershipRepository;
        this.chitGroupRepository = chitGroupRepository;
        this.subscriberRepository = subscriberRepository;
    }



    @Override
    public Membership createMembership(MembershipRequest membershipRequest) {

        ChitGroup chitGroup = chitGroupRepository.findByGroupId(membershipRequest.getChitGroupId())
                .orElseThrow(() -> new ResourceNotFoundException("ChitGroup not found with id: " + membershipRequest.getChitGroupId()));
        SubscriberEntity subscriberEntity = subscriberRepository.findByFirstNameAndLastName(membershipRequest.getSubscriberName().split(" ")[0], membershipRequest.getSubscriberName().split(" ")[1])
                .orElseThrow(() -> new ResourceNotFoundException("Subscriber not found with name: " + membershipRequest.getSubscriberName()));
        if(membershipRepository.existsByChitGroupIdAndSubscriberNameAndJoinedAt(chitGroup.getId(),membershipRequest.getSubscriberName(),membershipRequest.getJoinedAt())) {
            throw new DublicateRecordException("Membership already exists for the given ChitGroupId, SubscriberName, and JoinedAt");
        }
        Membership membership = new Membership();
        membership.setUserId(membershipRequest.getUserId());
        membership.setPremium(membershipRequest.getPremium());
        membership.setAgentName(membershipRequest.getAgentName());
        membership.setSubscriber(subscriberEntity);
        membership.setSubscriberName(membershipRequest.getSubscriberName());
        membership.setPremium(membershipRequest.getPremium());

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
    public List<MembershipRequest> getAllMemberships() {
        logger.info("Fetching all Memberships");

        List<Membership> memberships = membershipRepository.findAll();
        return memberships.stream()
                .map(membership -> {

                    return MembershipRequest.builder()
                            .agentName(membership.getAgentName())
                            .chitGroupId(membership.getChitGroup().getGroupId())
                            .premium(membership.getPremium())
                            .subscriberName(membership.getSubscriberName())
                            .chitGroupName(membership.getChitGroup().getChitGroupName())
                            .joinedAt(membership.getJoinedAt())
                     .build();
                })
                .collect(Collectors.toList());
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

    @Override
    public List<MembershipRequest> getMembershipsByJoinedAt(int month,int year) {
        logger.info("Fetching all Memberships by Joined month and year:{}", month+" "+year);

        List<Membership> memberships = membershipRepository.findByMonthAndYear(month, year);
        return memberships.stream()
                .map(membership -> {

                    return MembershipRequest.builder()
                            .agentName(membership.getAgentName())
                            .chitGroupId(membership.getChitGroup().getGroupId())
                            .premium(membership.getPremium())
                            .subscriberName(membership.getSubscriberName())
                            .chitGroupName(membership.getChitGroup().getChitGroupName())
                            .joinedAt(membership.getJoinedAt())
                            .build();
                })
                .collect(Collectors.toList());
    }
}