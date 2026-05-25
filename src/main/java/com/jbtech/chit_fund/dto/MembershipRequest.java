package com.jbtech.chit_fund.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MembershipRequest {

    private Long userId;
    private Long subscriberId;
    private String subscriberName;
    private String chitGroupName;
    private String chitGroupId;
    private String premium;
    private String agentName;
    private LocalDate joinedAt;

}