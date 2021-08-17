package com.example.onlineAuction.service;

import com.example.onlineAuction.dto.ProductDto;
import com.example.onlineAuction.mapper.ProductMapper;
import com.example.onlineAuction.model.Product;
import com.example.onlineAuction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ProductDto> getAllProductDtos() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> result = new ArrayList<>();
        for (Product product : productList) {
            ProductDto productDto = productMapper.map(product);
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

    public Optional<ProductDto> getProductDtoById(String productId) {
        Optional<Product> optionalProductFound = productRepository.findById(Integer.valueOf(productId));
        if (!optionalProductFound.isPresent()) {
            return Optional.empty();
        }
        Product productFound = optionalProductFound.get();
        ProductDto productDto = productMapper.map(productFound);
        return Optional.of(productDto);
    }
}
