package ru.job4j.ood.lcp.foodstore.store;

import ru.job4j.ood.lcp.foodstore.model.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {
    private final List<Food> products = new ArrayList<>();

    @Override
    public void add(Food food) {
        products.add(food);
    }

    @Override
    public List<Food> getProducts() {
        return products;
    }
}
