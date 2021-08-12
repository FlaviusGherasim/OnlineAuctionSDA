package com.example.onlineAuction.mapper;

import com.example.onlineAuction.dto.ProductDto;
import com.example.onlineAuction.model.Category;
import com.example.onlineAuction.model.Product;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class ProductMapper {

    public Product map(ProductDto productDto, MultipartFile multipartFile) {
        Product product = new Product();
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
        return product;
    }

    public ProductDto map(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId().toString());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setStartBiddingPrice(product.getStartBiddingPrice().toString());
        productDto.setEndDateTime((product.getEndDateTime().toString()));
        productDto.setCategory(product.getCategory().name());
        String imageAsString = Base64.encodeBase64String(product.getImage());
        productDto.setImage(imageAsString);
        return productDto;
    }
}
