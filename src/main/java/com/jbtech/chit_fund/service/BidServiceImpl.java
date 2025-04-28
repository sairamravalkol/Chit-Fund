package com.jbtech.chit_fund.service;

import com.jbtech.chit_fund.exception.ResourceNotFoundException;
import com.jbtech.chit_fund.model.Bid;
import com.jbtech.chit_fund.model.ChitGroup;
import com.jbtech.chit_fund.repository.BidRepository;
import com.jbtech.chit_fund.repository.ChitGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidService {

    private static final Logger logger = LoggerFactory.getLogger(BidServiceImpl.class);

    private final BidRepository bidRepository;
    private final ChitGroupRepository chitGroupRepository;

    public BidServiceImpl(BidRepository bidRepository, ChitGroupRepository chitGroupRepository) {
        this.bidRepository = bidRepository;
        this.chitGroupRepository = chitGroupRepository;
    }

    @Override
    public Bid createBid(Bid bid) {
        ChitGroup chitGroup = chitGroupRepository.findById(bid.getChitGroup().getId())
                .orElseThrow(() -> new ResourceNotFoundException("ChitGroup not found with id: " + bid.getChitGroup().getId()));

        bid.setChitGroup(chitGroup);
        logger.info("Creating a new Bid: {}", bid);
        return bidRepository.save(bid);
    }

    @Override
    public Optional<Bid> getBidById(Long id) {
        logger.info("Fetching Bid with id: {}", id);
        return Optional.ofNullable(bidRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Bid not found with id: {}", id);
                    return new ResourceNotFoundException("Bid not found with id: " + id);
                }));
    }

    @Override
    public List<Bid> getAllBids() {
        logger.info("Fetching all Bids");
        return bidRepository.findAll();
    }

    @Override
    public void deleteBid(Long id) {
        logger.info("Deleting Bid with id: {}", id);
        if (!bidRepository.existsById(id)) {
            logger.error("Bid not found with id: {}", id);
            throw new ResourceNotFoundException("Bid not found with id: " + id);
        }
        bidRepository.deleteById(id);
        logger.info("Deleted Bid with id: {}", id);
    }
}