package com.jbtech.chit_fund.service;

import com.jbtech.chit_fund.dto.SubscriberDTO;
import com.jbtech.chit_fund.model.SubscriberEntity;

import java.util.List;
import java.util.Optional;

public interface SubscriberService {
    String createSubscriber(SubscriberDTO subscriberDTO);
    Optional<SubscriberEntity> getSubscriberById(Long id);
    List<SubscriberDTO> getAllSubscribers();
    void deleteSubscriber(Long id);
//    String addMembershipToSubscriber(Long subscriberId, MembershipRequest membershipRequest);
//    List<Membership> getMembershipsBySubscriberId(Long subscriberId);
}
