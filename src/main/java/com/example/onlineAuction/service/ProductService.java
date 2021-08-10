package com.example.onlineAuction.service;

import com.example.onlineAuction.dto.ProductDto;
import com.example.onlineAuction.model.Category;
import com.example.onlineAuction.model.Product;
import com.example.onlineAuction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void addProduct(ProductDto productDto, MultipartFile multipartFile)
    {
        Product product= new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setStartBiddingPrice(Integer.valueOf(productDto.getStartBiddingPrice()));
        product.setEndDateTime(LocalDateTime.parse(productDto.getEndDateTime()));
        product.setCategory(Category.valueOf(productDto.getCategory()));
        try {
            product.setImage(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        productRepository.save(product);
    }

    public List<ProductDto> getAllProductDtos() {
        List<Product> productList= productRepository.findAll();
        List<ProductDto> result= new ArrayList<>();
        for (Product product: productList) {
            ProductDto productDto= new ProductDto();
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setStartBiddingPrice(product.getStartBiddingPrice().toString());
            productDto.setEndDateTime((product.getEndDateTime().toString()));
            productDto.setCategory(product.getCategory().name());
            result.add(productDto);
        }
        return result;
    }
}
