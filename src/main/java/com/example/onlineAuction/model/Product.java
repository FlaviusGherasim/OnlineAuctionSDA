package com.example.onlineAuction.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer startBiddingPrice;
//    @Enumerated(value = EnumType.STRING)
    private Category category;
    private LocalDateTime endDateTime;
    @Lob
    private byte[] image;
}
