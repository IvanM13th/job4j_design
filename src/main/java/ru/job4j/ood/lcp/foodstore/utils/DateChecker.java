package ru.job4j.ood.lcp.foodstore.utils;

import ru.job4j.ood.lcp.foodstore.model.Food;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateChecker implements FindDateDiff {
    @Override
    public double getExpPercentage(Food food) {
        double full = (double) Duration.between(food.getCreateDate(), food.getExpiryDate()).toDays();
        double current = (double) Duration.between(food.getCreateDate(), LocalDateTime.now()).toDays();
        return  current / full * 100;
    }
}