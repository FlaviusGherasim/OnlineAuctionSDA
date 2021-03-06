package com.example.onlineAuction.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private User winner;

    @Lob
    private byte[] image;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "product", fetch = FetchType.EAGER)
    private List<Bid> bidList;
}
