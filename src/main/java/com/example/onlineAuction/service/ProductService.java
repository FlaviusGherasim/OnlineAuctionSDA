package com.example.onlineAuction.service;

import com.example.onlineAuction.dto.ProductDto;
import com.example.onlineAuction.model.Category;
import com.example.onlineAuction.model.Product;
import com.example.onlineAuction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void addProduct(ProductDto productDto)
    {
        Product product= new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setStartBiddingPrice(Integer.valueOf(productDto.getStartBiddingPrice()));
        product.setEndDateTime(LocalDateTime.parse(productDto.getEndDateTime()));
//        product.setCategory(Category.valueOf(productDto.getCategory()));
        productRepository.save(product);

    }

}
