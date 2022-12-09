package ru.job4j.set;

import ru.job4j.collections.SimpleArrayList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    HashSet<T> s = new HashSet<>();

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean rsl = true;
        for (var o : set) {
            if (Objects.equals(value, o)) {
                rsl = false;
                break;
            }
        }
        if (rsl) {
            set.add(value);
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (var o : set) {
            if (Objects.equals(value, o)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

