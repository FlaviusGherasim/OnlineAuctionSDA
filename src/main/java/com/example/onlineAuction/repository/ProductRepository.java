package com.example.onlineAuction.repository;

import com.example.onlineAuction.model.Product;
import com.example.onlineAuction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByEndDateTimeAfter(LocalDateTime localDateTime);
//    List<Product> findAllByEndDateTimeBefore(LocalDateTime localDateTime);
    @Query("SELECT p from Product p WHERE p.endDateTime< :now and p.winner is null and p.bidList.size> 0")
    List<Product> findAllExpiredWithoutWinnersAssigned(@Param("now") LocalDateTime now);

    @Query("SELECT p from Product p where p.winner.email= :email")
    List<Product> findByWinnerEmail(@Param("email") String email);
}
