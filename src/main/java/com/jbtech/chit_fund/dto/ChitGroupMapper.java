package com.jbtech.chit_fund.dto;

import com.jbtech.chit_fund.model.ChitGroup;

public class ChitGroupMapper {

    public  static ChitGroupDTO fetchChitGroups(ChitGroup cGroup) {
        return ChitGroupDTO.builder()
                .chitGroupName(cGroup.getChitGroupName())
                .term(cGroup.getDuration())
                .id(cGroup.getGroupId())
                .premium(cGroup.getPremium())
                .sumAssured(cGroup.getSumAssured())
                .startDate(cGroup.getStartDate())
                .build();
    }
}
