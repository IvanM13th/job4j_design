package ru.job4j.kiss;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MaxMinTest {

    @Test
    public void whenListOfIntegersAndFindMax() {

        List<Integer> list = List.of(7, 4, 6, 22, 34, 1, 123, 245, 5);
        int max = new MaxMin().max(list, Comparator.naturalOrder());
        int rsl = 245;
        assertThat(max).isEqualTo(rsl);
    }

    @Test
    public void whenListOfIntegersAndFindMin() {

        List<Integer> list = List.of(7, 4, 6, 22, 34, 1, 123, 245, 5);
        int max = new MaxMin().min(list, Comparator.naturalOrder());
        int rsl = 1;
        assertThat(max).isEqualTo(rsl);
    }

    @Test
    public void whenListOfStringAndFindMax() {
        List<String> list = List.of("1", "10", "100", "1000");
        String max = new MaxMin().max(list, Comparator.naturalOrder());
        String rsl = "1000";
        assertThat(max).isEqualTo(rsl);
    }

    @Test
    public void whenListOfStringAndFindMin() {
        List<String> list = List.of("1", "10", "100", "1000");
        String max = new MaxMin().min(list, Comparator.naturalOrder());
        String rsl = "1";
        assertThat(max).isEqualTo(rsl);
    }

    @Test
    public void whenMaxAndListIsEmpty() {
        Assertions.assertThatThrownBy(() -> new MaxMin().max(new ArrayList<String>(), Comparator.naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenMinAndListIsEmpty() {
        Assertions.assertThatThrownBy(() -> new MaxMin().min(new ArrayList<String>(), Comparator.naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
