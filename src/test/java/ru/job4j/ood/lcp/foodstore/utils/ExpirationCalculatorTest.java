package ru.job4j.ood.lcp.foodstore.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.withPrecision;
import ru.job4j.ood.lcp.foodstore.model.Milk;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import static org.assertj.core.api.Assertions.assertThat;

public class ExpirationCalculatorTest {

    @Test
    public void whenCheckExp() {
        Milk milk = new Milk("milk",
                LocalDateTime.of(2023, Month.JANUARY, 1, 1, 1, 1, 1),
                LocalDateTime.of(2023, Month.JANUARY, 31, 1, 1, 1, 1), 500, 10);
        LocalDateExpirationDate localDateExpirationDate = new LocalDateExpirationDate();
        double full = (double) Duration.between(milk.getCreateDate(), milk.getExpiryDate()).toDays();
        double current = (double) Duration.between(milk.getCreateDate(), LocalDateTime.now()).toDays();
        assertThat(localDateExpirationDate.getExpPercentage(milk.getCreateDate(), milk.getExpiryDate())).
                isEqualTo(current / full * 100, withPrecision(0.005d));

    }
}