package com.jbtech.chit_fund.service;

import com.jbtech.chit_fund.model.ChitGroupTemplate;

import java.util.List;
import java.util.Optional;

public interface ChitGroupTemplateService {
    ChitGroupTemplate create(ChitGroupTemplate chitGroupTemplate);

    List<ChitGroupTemplate> getAll();

    Optional<ChitGroupTemplate> getById(Long id);

    ChitGroupTemplate update(Long id, ChitGroupTemplate chitGroupTemplate);

    void delete(Long id);
}
