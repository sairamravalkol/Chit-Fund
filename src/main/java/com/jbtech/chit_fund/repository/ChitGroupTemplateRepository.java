package com.jbtech.chit_fund.repository;


import com.jbtech.chit_fund.model.ChitGroupTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChitGroupTemplateRepository extends JpaRepository<ChitGroupTemplate, Long> {
}
