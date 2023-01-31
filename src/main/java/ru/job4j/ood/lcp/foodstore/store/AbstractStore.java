package ru.job4j.ood.lcp.foodstore.store;

import ru.job4j.ood.lcp.foodstore.model.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    protected final List<Food> products = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (isNotExpired(food)) {
            rsl = products.add(food);
        }
        return rsl;
    }

    protected abstract boolean isNotExpired(Food food);

    public List<Food> getProducts() {
        List<Food> copy = List.copyOf(products);
        return copy;
    }

    public void clear() {
        products.clear();
    }
}