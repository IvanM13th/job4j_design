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
        checkEmptyContainer();
        ensureCapacity(size + 1);
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        checkEmptyContainer();
        ensureCapacity(size + 1);
        Objects.checkIndex(index, size);
        System.arraycopy(container, index, container, index + 1, size - index);
        container[index] = newValue;
        return container[index + 1];
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        var rsl = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[size - 1] = null;
        size--;
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

    public void ensureCapacity(int size) {
        if (size > container.length) {
            int newCapacity = container.length * 2;
            this.container = Arrays.copyOf(container, newCapacity);
        }
    }

    public void checkEmptyContainer() {
        if (container.length == 0) {
            this.container = Arrays.copyOf(container, 1);
        }
    }
}
