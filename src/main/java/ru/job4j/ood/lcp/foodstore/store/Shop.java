package ru.job4j.ood.lcp.foodstore.store;

import ru.job4j.ood.lcp.foodstore.model.Food;
import ru.job4j.ood.lcp.foodstore.utils.ExpirationCalculator;

import java.time.LocalDateTime;

public class Shop extends AbstractStore {
    public static final double PRICE_THRESHOLD = 75;
    private final ExpirationCalculator<LocalDateTime> expirationCalculator;

    public Shop(ExpirationCalculator<LocalDateTime> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean isNotExpired(Food food) {
        boolean rsl = false;
        double expPercentage = expirationCalculator.getExpPercentage(food.getCreateDate(), food.getExpiryDate());
        if (expPercentage > Warehouse.BOTTOM_THRESHOLD && expPercentage <= Trash.BOTTOM_THRESHOLD) {
            if (expPercentage > PRICE_THRESHOLD) {
                food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount() / 100));
            }
            rsl = true;
        }
        return rsl;
    }
}