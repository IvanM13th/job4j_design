package ru.job4j.ood.lcp.foodstore.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class LocalDateTimeExpirationDate implements ExpirationCalculator<LocalDateTime> {

    @Override
    public double getExpPercentage(LocalDateTime startDate, LocalDateTime endDate) {

        double full = (double) Duration.between(startDate, endDate).toDays();
        double current = (double) Duration.between(startDate, LocalDateTime.now()).toDays();
        return  current / full * 100;
    }
}
