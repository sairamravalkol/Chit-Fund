package com.jbtech.chit_fund.service;

import com.jbtech.chit_fund.model.ChitGroup;

import java.util.List;
import java.util.Optional;

public interface ChitGroupService {
    ChitGroup createChitGroup(ChitGroup chitGroup);
    Optional<ChitGroup> getChitGroupById(Long id);
    List<ChitGroup> getAllChitGroups();
    ChitGroup updateChitGroup(Long id, ChitGroup chitGroup);
    void deleteChitGroup(Long id);
}