package ru.job4j.collections;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int countIn = 0;
    private int countOut = 0;

    public T poll() {
        T rsl = null;
        if (countIn == 0 && countOut == 0) {
            throw new NoSuchElementException();
        }
        if (countOut == 0) {
            rsl = in.pop();
            countIn--;
        } else if (countOut != 0) {
            rsl = out.pop();
            countOut--;
        }
        return rsl;
    }

    public void push(T value) {
        if (countIn == 0) {
            in.push(value);
            countIn++;
        } else {
            while (countIn != 0) {
                out.push(poll());
                countOut++;
            }
            in.push(value);
            countIn++;
            while (countOut != 0) {
                in.push(poll());
                countIn++;
            }
        }
    }
}


