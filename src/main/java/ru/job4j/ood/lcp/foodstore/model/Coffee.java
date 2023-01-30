package ru.job4j.ood.lcp.foodstore.model;

import java.time.LocalDateTime;

public class Coffee extends Food {
    public Coffee(String name, LocalDateTime createDate, LocalDateTime expiryDate, double price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
