package com.jbtech.chit_fund.service;

import com.jbtech.chit_fund.exception.ResourceNotFoundException;
import com.jbtech.chit_fund.model.ChitGroup;
import com.jbtech.chit_fund.repository.ChitGroupRepository;
import com.jbtech.chit_fund.service.ChitGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChitGroupServiceImpl implements ChitGroupService {

    private static final Logger logger = LoggerFactory.getLogger(ChitGroupServiceImpl.class);

    private final ChitGroupRepository chitGroupRepository;

    public ChitGroupServiceImpl(ChitGroupRepository chitGroupRepository) {
        this.chitGroupRepository = chitGroupRepository;
    }

    @Override
    public ChitGroup createChitGroup(ChitGroup chitGroup) {
        logger.info("Creating a new ChitGroup: {}", chitGroup);
        return chitGroupRepository.save(chitGroup);
    }

    @Override
    public Optional<ChitGroup> getChitGroupById(Long id) {
        logger.info("Fetching ChitGroup with id: {}", id);
        return Optional.ofNullable(chitGroupRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("ChitGroup not found with id: {}", id);
                    return new ResourceNotFoundException("ChitGroup not found with id: " + id);
                }));
    }

    @Override
    public List<ChitGroup> getAllChitGroups() {
        logger.info("Fetching all ChitGroups");
        return chitGroupRepository.findAll();
    }

    @Override
    public ChitGroup updateChitGroup(Long id, ChitGroup chitGroup) {
        logger.info("Updating ChitGroup with id: {}", id);
        return chitGroupRepository.findById(id)
                .map(existingChitGroup -> {
                    logger.debug("Existing ChitGroup: {}", existingChitGroup);
                    existingChitGroup.setName(chitGroup.getName());
                    existingChitGroup.setAmount(chitGroup.getAmount());
                    existingChitGroup.setDuration(chitGroup.getDuration());
                    existingChitGroup.setStartDate(chitGroup.getStartDate());
                    existingChitGroup.setIsActive(chitGroup.getIsActive());
                    ChitGroup updatedChitGroup = chitGroupRepository.save(existingChitGroup);
                    logger.info("Updated ChitGroup: {}", updatedChitGroup);
                    return updatedChitGroup;
                })
                .orElseThrow(() -> {
                    logger.error("ChitGroup not found with id: {}", id);
                    return new ResourceNotFoundException("ChitGroup not found with id: " + id);
                });
    }

    @Override
    public void deleteChitGroup(Long id) {
        logger.info("Deleting ChitGroup with id: {}", id);
        if (!chitGroupRepository.existsById(id)) {
            logger.error("ChitGroup not found with id: {}", id);
            throw new ResourceNotFoundException("ChitGroup not found with id: " + id);
        }
        chitGroupRepository.deleteById(id);
        logger.info("Deleted ChitGroup with id: {}", id);
    }
}