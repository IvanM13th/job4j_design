package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compare(value, (a, b) -> comparator.compare(a, b) < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compare(value, (a, b) -> comparator.compare(a, b) > 0);
    }

    public <T> T compare(List<T> list, BiPredicate<T, T> predicate) {
        validate(list);
        T rsl = list.get(0);
        for (var value : list) {
            if (predicate.test(rsl, value)) {
                rsl = value;
            }
        }
        return rsl;
    }

    private <T> void validate(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }
    }

}