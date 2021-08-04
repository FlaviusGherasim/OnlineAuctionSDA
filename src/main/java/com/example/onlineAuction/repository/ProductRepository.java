package com.example.onlineAuction.repository;

import com.example.onlineAuction.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
