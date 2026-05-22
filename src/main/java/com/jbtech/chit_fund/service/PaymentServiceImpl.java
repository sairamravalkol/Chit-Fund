package com.jbtech.chit_fund.service;

import com.jbtech.chit_fund.exception.ResourceNotFoundException;
import com.jbtech.chit_fund.model.Payment;
import com.jbtech.chit_fund.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(Payment payment) {
        logger.info("Creating a new Payment: {}", payment);
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        logger.info("Fetching Payment with id: {}", id);
        return Optional.ofNullable(paymentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Payment not found with id: {}", id);
                    return new ResourceNotFoundException("Payment not found with id: " + id);
                }));
    }

    @Override
    public List<Payment> getAllPayments() {
        logger.info("Fetching all Payments");
        return paymentRepository.findAll();
    }

    @Override
    public void deletePayment(Long id) {
        logger.info("Deleting Payment with id: {}", id);
        if (!paymentRepository.existsById(id)) {
            logger.error("Payment not found with id: {}", id);
            throw new ResourceNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
        logger.info("Deleted Payment with id: {}", id);
    }
}