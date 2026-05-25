package com.jbtech.chit_fund.service.impl;

import com.jbtech.chit_fund.dto.SubscriberDTO;
import com.jbtech.chit_fund.exception.ResourceNotFoundException;
import com.jbtech.chit_fund.model.SubscriberEntity;
import com.jbtech.chit_fund.repository.ChitGroupRepository;
import com.jbtech.chit_fund.repository.SubscriberRepository;
import com.jbtech.chit_fund.service.SubscriberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    private static final Logger logger = LoggerFactory.getLogger(SubscriberServiceImpl.class);

    private final SubscriberRepository subscriberRepository;
    ChitGroupRepository chitGroupRepository;

    public SubscriberServiceImpl(SubscriberRepository subscriberRepository, ChitGroupRepository chitGroupRepository) {
        this.subscriberRepository = subscriberRepository;
        this.chitGroupRepository = chitGroupRepository;
    }



    @Override
    public String createSubscriber(SubscriberDTO subscriberDTO) {


        SubscriberEntity entity = SubscriberEntity.builder()
                .firstName(subscriberDTO.getFirstName())
                .lastName(subscriberDTO.getLastName())
                .emailId(subscriberDTO.getEmailId())
                .phoneNumber1(subscriberDTO.getPhoneNumber1())
                .phoneNumber2(subscriberDTO.getPhoneNumber2())
                .address(subscriberDTO.getAddress())
                .fatherSpouseName(subscriberDTO.getFatherSpouseName())
                .annualIncome(subscriberDTO.getAnnualIncome())
                .incomeSource(subscriberDTO.getIncomeSource())
                .occupation(subscriberDTO.getOccupation())
                .occupation(subscriberDTO.getOccupation())
                .idProofAadharNo(subscriberDTO.getIdProofAadharNo())
                .nomineeName(subscriberDTO.getNomineeName())
                .nomineePhone(subscriberDTO.getNomineePhone())
                .relationWithNominee(subscriberDTO.getRelationWithNominee())
                .referenceName1(subscriberDTO.getReferenceName1())
                .referencePhone1(subscriberDTO.getReferencePhone1())
                .referenceName2(subscriberDTO.getReferenceName2())
                .referencePhone2(subscriberDTO.getReferencePhone2())
                .build();



        logger.info("Creating a new Subscriber: {}", entity);
         subscriberRepository.save(entity);
        return "Subscriber created successfully with id: " + entity.getId();
    }

    @Override
    public Optional<SubscriberEntity> getSubscriberById(Long id) {
        logger.info("Fetching Subscriber with id: {}", id);
        return Optional.ofNullable(subscriberRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Subscriber not found with id: {}", id);
                    return new ResourceNotFoundException("Subscriber not found with id: " + id);
                }));
    }

    @Override
    public List<SubscriberDTO> getAllSubscribers() {
        logger.info("Fetching all Subscribers");
        return subscriberRepository.findAll().stream()
                .map(subscriber -> SubscriberDTO.builder()
                        .firstName(subscriber.getFirstName())
                        .lastName(subscriber.getLastName())
                        .emailId(subscriber.getEmailId())
                        .phoneNumber1(subscriber.getPhoneNumber1())
                        .phoneNumber2(subscriber.getPhoneNumber2())
                        .address(subscriber.getAddress())
                        .fatherSpouseName(subscriber.getFatherSpouseName())
                        .annualIncome(subscriber.getAnnualIncome())
                        .incomeSource(subscriber.getIncomeSource())
                        .occupation(subscriber.getOccupation())
                        .idProofAadharNo(subscriber.getIdProofAadharNo())
                        .nomineeName(subscriber.getNomineeName())
                        .relationWithNominee(subscriber.getRelationWithNominee())
                        .referenceName1(subscriber.getReferenceName1())
                        .referencePhone1(subscriber.getReferencePhone1())
                        .referenceName2(subscriber.getReferenceName2())
                        .referencePhone2(subscriber.getReferencePhone2())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubscriber(Long id) {
        logger.info("Deleting Subscriber with id: {}", id);
        if (!subscriberRepository.existsById(id)) {
            logger.error("Subscriber not found with id: {}", id);
            throw new ResourceNotFoundException("Subscriber not found with id: " + id);
        }
        subscriberRepository.deleteById(id);
        logger.info("Deleted Subscriber with id: {}", id);
    }
}