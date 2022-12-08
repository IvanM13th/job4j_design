package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveHigherThanOne() {
        Predicate<Integer> f = s -> s > 1;
        ListUtils.removeIf(input, f);
        assertThat(input).containsOnly(1);
    }

    @Test
    void whenReplaceAllPositiveNumbers() {
        Predicate<Integer> f = s -> s > 0;
        ListUtils.replaceIf(input, f, 99);
        assertThat(input).containsSequence(99, 99);
    }

    @Test
    void whenRemoveDuplicates() {
        input.add(10);
        input.add(11);
        input.add(13);
        List<Integer> elem = List.of(
                10,
                13
        );
        ListUtils.removeAll(input, elem);
        assertThat(input).containsOnly(1, 3, 11);

    }
}