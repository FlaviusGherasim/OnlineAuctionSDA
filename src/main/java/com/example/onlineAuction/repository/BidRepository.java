package com.example.onlineAuction.repository;

import com.example.onlineAuction.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Integer> {
}
