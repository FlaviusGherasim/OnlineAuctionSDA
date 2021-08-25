package com.example.onlineAuction.mapper;

import com.example.onlineAuction.dto.BidDto;
import com.example.onlineAuction.model.Bid;
import com.example.onlineAuction.model.Product;
import com.example.onlineAuction.model.User;
import com.example.onlineAuction.repository.ProductRepository;
import com.example.onlineAuction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidMapper {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public Bid map(BidDto bidDto, String productId, String userEmail) {
        Bid bid = new Bid();
        bid.setValue(Integer.valueOf(bidDto.getValue()));
        Optional<Product> optionalProduct = productRepository.findById(Integer.valueOf(productId));
        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Product ID: " + productId + " is not valid");
        }
        bid.setProduct(optionalProduct.get());
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User email: " + userEmail + " is not valid");
        }
        bid.setUser(optionalUser.get());
        return bid;
    }
}
