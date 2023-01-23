package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MaxMinTest {

    @Test
    public void whenListOfIntegersAndFindMax() {

        List<Integer> list = List.of(7, 4, 6, 22, 34, 1, 123, 245, 5);
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        int max = new MaxMin().max(list, comparator);
        int rsl = 245;
        assertThat(max).isEqualTo(rsl);
    }

    @Test
    public void whenListOfIntegersAndFindMin() {

        List<Integer> list = List.of(7, 4, 6, 22, 34, 1, 123, 245, 5);
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        int max = new MaxMin().min(list, comparator);
        int rsl = 1;
        assertThat(max).isEqualTo(rsl);
    }

    @Test
    public void whenListOfStringAndFindMax() {
        List<String> list = List.of("1", "10", "100", "1000");
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        String max = new MaxMin().max(list, comparator);
        String rsl = "1000";
        assertThat(max).isEqualTo(rsl);
    }

    @Test
    public void whenListOfStringAndFindMin() {
        List<String> list = List.of("1", "10", "100", "1000");
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        String max = new MaxMin().min(list, comparator);
        String rsl = "1";
        assertThat(max).isEqualTo(rsl);
    }
}
