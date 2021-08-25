package com.example.onlineAuction.validator;

import com.example.onlineAuction.dto.BidDto;
import com.example.onlineAuction.model.Bid;
import com.example.onlineAuction.model.Product;
import com.example.onlineAuction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

@Service
public class BidDtoValidator {

    @Autowired
    private ProductRepository productRepository;

    public void validate(BidDto bidDto, BindingResult bindingResult, String productId) {

        String bidValue = bidDto.getValue();
        if (bidValue.isEmpty()) {

            FieldError fieldError = new FieldError("bidDto", "value", "Value should not be empty");
            bindingResult.addError(fieldError);
            return;
        }

        Integer bidValueAsNumber = 0;
        try {
            bidValueAsNumber = Integer.valueOf(bidValue);
        } catch (NumberFormatException e) {
            FieldError fieldError = new FieldError("bidDto", "value", "The Bid must a number.");
            bindingResult.addError(fieldError);
            return;
        }
        if (bidValueAsNumber <= 0) {
            FieldError fieldError = new FieldError("bidDto", "value", "The Bid must be positive.");
            bindingResult.addError(fieldError);
            return;
        }

        Optional<Product> optionalProduct = productRepository.findById(Integer.valueOf(productId));
        if (!optionalProduct.isPresent()) {
            FieldError fieldError = new FieldError("bidDto", "value", "Product Id is not valid.");
            bindingResult.addError(fieldError);
            return;
        }
        Product product = optionalProduct.get();
        if (product.getStartBiddingPrice() > bidValueAsNumber) {
            FieldError fieldError = new FieldError("bidDto", "value", "Bid must be at least starting price.");
            bindingResult.addError(fieldError);
            return;
        }

        List<Bid> bidList = product.getBidList();
        if (!bidList.isEmpty()) {
            int max = 0;
            for (Bid bid : bidList) {
                if (max< bid.getValue()) {
                    max = bid.getValue();
                }
            }
            if (bidValueAsNumber<=max){
                FieldError fieldError = new FieldError("bidDto", "value", "Bid must be greater than latest bid.");
                bindingResult.addError(fieldError);
            }
        }
    }
}
