package com.jbtech.chit_fund.service;

import com.jbtech.chit_fund.exception.ResourceNotFoundException;
import com.jbtech.chit_fund.model.Payout;
import com.jbtech.chit_fund.repository.PayoutRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PayoutServiceImpl implements PayoutService {

    private static final Logger logger = LoggerFactory.getLogger(PayoutServiceImpl.class);

    private final PayoutRepository payoutRepository;

    public PayoutServiceImpl(PayoutRepository payoutRepository) {
        this.payoutRepository = payoutRepository;
    }

    @Override
    public Payout createPayout(Payout payout) {
        logger.info("Creating a new Payout: {}", payout);
        return payoutRepository.save(payout);
    }

    @Override
    public Optional<Payout> getPayoutById(Long id) {
        logger.info("Fetching Payout with id: {}", id);
        return Optional.ofNullable(payoutRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Payout not found with id: {}", id);
                    return new ResourceNotFoundException("Payout not found with id: " + id);
                }));
    }

    @Override
    public List<Payout> getAllPayouts() {
        logger.info("Fetching all Payouts");
        return payoutRepository.findAll();
    }

    @Override
    public void deletePayout(Long id) {
        logger.info("Deleting Payout with id: {}", id);
        if (!payoutRepository.existsById(id)) {
            logger.error("Payout not found with id: {}", id);
            throw new ResourceNotFoundException("Payout not found with id: " + id);
        }
        payoutRepository.deleteById(id);
        logger.info("Deleted Payout with id: {}", id);
    }
}