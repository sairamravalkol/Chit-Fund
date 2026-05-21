package com.jbtech.chit_fund.service;

import com.jbtech.chit_fund.dto.ChitGroupDTO;
import com.jbtech.chit_fund.dto.ChitGroupMapper;
import com.jbtech.chit_fund.exception.ResourceNotFoundException;
import com.jbtech.chit_fund.model.ChitGroup;
import com.jbtech.chit_fund.repository.ChitGroupRepository;
import com.jbtech.chit_fund.service.ChitGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChitGroupServiceImpl implements ChitGroupService {

    private static final Logger logger = LoggerFactory.getLogger(ChitGroupServiceImpl.class);

    private final ChitGroupRepository chitGroupRepository;

    public ChitGroupServiceImpl(ChitGroupRepository chitGroupRepository) {
        this.chitGroupRepository = chitGroupRepository;
    }

    @Override
    public ChitGroup createChitGroup(ChitGroupDTO chitGroup) {
        logger.info("Creating a new ChitGroup: {}", chitGroup);
        ChitGroup entity = ChitGroup.builder()
                .sumAssured(chitGroup.getSumAssured())
                .chitGroupName(chitGroup.getChitGroupName())
                .groupId(chitGroup.getId())
                .startDate(chitGroup.getStartDate())
                .premium(chitGroup.getPremium())
                .isActive("YES")
                .duration(chitGroup.getTerm())
                .build();

        return chitGroupRepository.save(entity);
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
    public List<ChitGroupDTO> getAllChitGroups() {
        logger.info("Fetching all ChitGroups");

         List<ChitGroup> entities =  chitGroupRepository.findAll();
        return entities.stream()
                 .map(ChitGroupMapper::fetchChitGroups)
                 .collect(Collectors.toList());

    }

//    @Override
//    public ChitGroup updateChitGroup(Long id, ChitGroup chitGroup) {
//        logger.info("Updating ChitGroup with id: {}", id);
//        return chitGroupRepository.findById(id)
//                .map(existingChitGroup -> {
//                    logger.debug("Existing ChitGroup: {}", existingChitGroup);
//                    existingChitGroup.setName(chitGroup.getName());
//                    existingChitGroup.setAmount(chitGroup.getAmount());
//                    existingChitGroup.setDuration(chitGroup.getDuration());
//                    existingChitGroup.setStartDate(chitGroup.getStartDate());
//                    existingChitGroup.setIsActive(chitGroup.getIsActive());
//                    ChitGroup updatedChitGroup = chitGroupRepository.save(existingChitGroup);
//                    logger.info("Updated ChitGroup: {}", updatedChitGroup);
//                    return updatedChitGroup;
//                })
//                .orElseThrow(() -> {
//                    logger.error("ChitGroup not found with id: {}", id);
//                    return new ResourceNotFoundException("ChitGroup not found with id: " + id);
//                });
//    }

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