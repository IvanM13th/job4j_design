package ru.job4j.collections;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        checkContainer();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        var rsl = this.get(index);
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        var rsl = this.get(index);
        size--;
        System.arraycopy(container, index + 1, container, index, size - index);
        container[size] = null;
        modCount++;
        return rsl;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            final int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }

    private void checkContainer() {
        if (container.length == 0) {
            this.container = Arrays.copyOf(container, 1);
        } else if (size > container.length - 1) {
            int newCapacity = container.length * 2;
            this.container = Arrays.copyOf(container, newCapacity);
        }
    }
}
