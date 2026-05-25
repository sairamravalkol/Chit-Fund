package com.jbtech.chit_fund.repository;

import com.jbtech.chit_fund.model.Membership;
import com.jbtech.chit_fund.model.SubscriberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long> {

    Optional<SubscriberEntity> findByFirstNameAndLastName(String firstName, String lastName);
}