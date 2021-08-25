package com.example.onlineAuction.utils;

import com.example.onlineAuction.model.Bid;

import java.util.Comparator;

public class BidComparator implements Comparator<Bid> {
    @Override
    public int compare(Bid bid1, Bid bid2) {
        //one way to compare
        if (bid1.getValue().compareTo(bid2.getValue())<0) {
            return -1;
            //another way to compare
        } else if (bid1.getValue() > bid2.getValue()) {
            return 1;
        }
        return 0;
    }
}
