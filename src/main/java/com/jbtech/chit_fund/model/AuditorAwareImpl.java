package com.jbtech.chit_fund.model;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        // Replace with Spring Security username
        return Optional.of("SYSTEM");
    }
}