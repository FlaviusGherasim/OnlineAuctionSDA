package com.example.onlineAuction.config;

import com.example.onlineAuction.model.Bid;
import com.example.onlineAuction.model.Product;
import com.example.onlineAuction.model.User;
import com.example.onlineAuction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private ProductRepository productRepository;

    @Scheduled(fixedDelay = 5000)
    public void cronJob(){
        List<Product> expiredProducts= productRepository.findAllExpiredWithoutWinnersAssigned(LocalDateTime.now());
        System.out.println("We found: "+ expiredProducts.size()+ " products eligible for winner assignment");

        for (Product product: expiredProducts) {
            Integer max=0;
            User winner= null;
            for (Bid bid: product.getBidList()) {
                if(max< bid.getValue()){
                    max= bid.getValue();
                    winner= bid.getUser();
                }
            }
            product.setWinner(winner);
            productRepository.save(product);
        }
    }
}
