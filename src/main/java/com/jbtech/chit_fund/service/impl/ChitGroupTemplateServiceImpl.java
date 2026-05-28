package com.jbtech.chit_fund.service.impl;

import com.jbtech.chit_fund.model.ChitGroupTemplate;
import com.jbtech.chit_fund.repository.ChitGroupTemplateRepository;
import com.jbtech.chit_fund.service.ChitGroupTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChitGroupTemplateServiceImpl  implements ChitGroupTemplateService {

    @Autowired
    private ChitGroupTemplateRepository repository;

    @Override
    public ChitGroupTemplate create(ChitGroupTemplate chitGroupTemplate) {
        return repository.save(chitGroupTemplate);
    }

    @Override
    public List<ChitGroupTemplate> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ChitGroupTemplate> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public ChitGroupTemplate update(Long id, ChitGroupTemplate chitGroupTemplate) {
        return repository.findById(id).map(existing -> {
            existing.setGroupId(chitGroupTemplate.getGroupId());
            existing.setPremiumMonthly(chitGroupTemplate.getPremiumMonthly());
            existing.setChitGroupName(chitGroupTemplate.getChitGroupName());
            existing.setSumAssured(chitGroupTemplate.getSumAssured());
            existing.setNoOfMonth(chitGroupTemplate.getNoOfMonth());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("ChitGroupTemplate not found with id: " + id));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
