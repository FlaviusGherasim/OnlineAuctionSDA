package com.example.onlineAuction.service;

import com.example.onlineAuction.dto.BidDto;
import com.example.onlineAuction.mapper.BidMapper;
import com.example.onlineAuction.model.Bid;
import com.example.onlineAuction.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private BidMapper bidMapper;

    public void placeBid(BidDto bidDto, String productId, String userEmail) {
        System.out.println("Adaug valoarea " + bidDto.getValue() + " pentru produsul cu id-ul " + productId
                + " si utilizatorul cu e-mailul " + userEmail);

        Bid bid = bidMapper.map(bidDto, productId, userEmail);
        bidRepository.save(bid);

    }

//        List<Bid> allBids = bidRepository.findAll();
//        return Collections.max(allBids);
}
