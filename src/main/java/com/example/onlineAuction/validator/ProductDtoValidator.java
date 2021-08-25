package com.example.onlineAuction.validator;

import com.example.onlineAuction.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;

@Service
public class ProductDtoValidator {

    public void validate(ProductDto productDto, BindingResult bindingResult) {
        validatePrice(productDto, bindingResult);
        validateDate(productDto, bindingResult);
    }

    private void validatePrice(ProductDto productDto, BindingResult bindingResult) {
        String priceAsString = productDto.getStartBiddingPrice();
        try {
            Integer priceAsInteger = Integer.valueOf(priceAsString);
            if (priceAsInteger <= 0) {
                FieldError fieldError= new FieldError("productDto","startBiddingPrice", "The price must be positive.");
                bindingResult.addError(fieldError);
            }
        } catch (NumberFormatException e) {
            FieldError fieldError= new FieldError("productDto","startBiddingPrice", "The price must a number.");
            bindingResult.addError(fieldError);
        }
    }

    private void validateDate(ProductDto productDto, BindingResult bindingResult) {
        LocalDateTime endingBidDate = LocalDateTime.parse(productDto.getEndDateTime());
            if (endingBidDate.isBefore(LocalDateTime.now().plusDays(1))) {
                FieldError fieldError= new FieldError("productDto","endDateTime", "The date must be after today.");
                bindingResult.addError(fieldError);
            }
    }

//    public boolean isValid(ProductDto productDto, BindingResult bindingResult) {
//        String priceAsString = productDto.getStartBiddingPrice();
//        try {
//            Integer priceAsInteger = Integer.valueOf(priceAsString);
//            if (priceAsInteger <= 0) {
//                return false;
//            }
//        } catch (NumberFormatException e) {
//            return false;
//        }
//        return true;
//    }
}
