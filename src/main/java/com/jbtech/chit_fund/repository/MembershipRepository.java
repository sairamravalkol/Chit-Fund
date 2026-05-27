package com.jbtech.chit_fund.repository;

import com.jbtech.chit_fund.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

    @Query("""
           SELECT m FROM Membership m
           WHERE MONTH(m.joinedAt) = :month
           AND YEAR(m.joinedAt) = :year
           """)
    List<Membership> findByMonthAndYear(
            @Param("month") int month,
            @Param("year") int year);

    Boolean existsByChitGroupIdAndSubscriberNameAndJoinedAt(Long chitGroupId, String subscriberName, LocalDate joinedAt);
}