package ru.job4j.ood.lcp.foodstore.store;

import ru.job4j.ood.lcp.foodstore.model.Food;
import ru.job4j.ood.lcp.foodstore.utils.ExpirationCalculator;

import java.time.LocalDateTime;

public class Warehouse extends AbstractStore {
    public static final double BOTTOM_THRESHOLD = 25;

    private final ExpirationCalculator<LocalDateTime> expirationCalculator;

    public Warehouse(ExpirationCalculator<LocalDateTime> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean isNotExpired(Food food) {
        boolean rsl = false;
        double expPercentage = expirationCalculator.getExpPercentage(food.getCreateDate(), food.getExpiryDate());
        if (expPercentage <= BOTTOM_THRESHOLD) {
            rsl = true;
        }
        return rsl;
    }
}