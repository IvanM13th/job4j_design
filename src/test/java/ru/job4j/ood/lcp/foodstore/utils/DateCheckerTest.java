package ru.job4j.ood.lcp.foodstore.utils;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lcp.foodstore.model.Food;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class DateCheckerTest {

    @Test
    public void whenCheckExp() {
        Food food = new Food("milk",
                LocalDateTime.of(2023, Month.JANUARY, 1, 1, 1, 1, 1),
                LocalDateTime.of(2023, Month.JANUARY, 31, 1, 1, 1, 1), 500, 10);
        DateChecker dateChecker = new DateChecker();
        assertThat(dateChecker.getExpPercentage(food)).isEqualTo(80.0);
    }
}