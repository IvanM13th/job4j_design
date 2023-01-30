package ru.job4j.ood.lcp.foodstore.model;

import java.time.LocalDateTime;

public class Tea extends Food {
    public Tea(String name, LocalDateTime createDate, LocalDateTime expiryDate, double price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
