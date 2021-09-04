package com.example.onlineAuction.service;

import com.example.onlineAuction.dto.ProductDto;
import com.example.onlineAuction.mapper.ProductMapper;
import com.example.onlineAuction.model.Product;
import com.example.onlineAuction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public void addProduct(ProductDto productDto, MultipartFile multipartFile) {

        Product product = productMapper.map(productDto, multipartFile);
        productRepository.save(product);
    }

    public List<ProductDto> getAllActiveProductDtos(String email) {

        List<Product> productList = productRepository.findAllByEndDateTimeAfter(LocalDateTime.now());
        List<ProductDto> result = new ArrayList<>();
        for (Product product : productList) {
            ProductDto productDto = productMapper.map(product, email);
            result.add(productDto);
        }
        return result;
    }

    public List<ProductDto> getAllProductDtos(String email) {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> result = new ArrayList<>();
        for (Product product : productList) {
            ProductDto productDto = productMapper.map(product, email);
            result.add(productDto);
        }
        return result;
    }

//    public List<ProductDto> getAllProductDtosWithStream() {
//        List<Product> productList = productRepository.findAll();
//        return productList.stream()
//                .map(productMapper::map)
//                .collect(Collectors.toList());
//    }

    public Optional<ProductDto> getProductDtoById(String productId, String email) {
        Optional<Product> optionalProductFound = productRepository.findById(Integer.valueOf(productId));
        if (!optionalProductFound.isPresent()) {
            return Optional.empty();
        }
        Product productFound = optionalProductFound.get();
        ProductDto productDto = productMapper.map(productFound, email);
        return Optional.of(productDto);
    }


    public List<ProductDto> getProductDtosFor(String email) {
        List<Product> productList = productRepository.findByWinnerEmail(email);
        List<ProductDto> result = new ArrayList<>();
        for (Product product : productList) {
            ProductDto productDto = productMapper.map(product, email);
            result.add(productDto);
        }
        return result;
    }
}
