package ru.job4j.ood.lcp.foodstore.utils;

public interface ExpirationCalculator<T> {
    double getExpPercentage(T startDate, T endDate);
}
