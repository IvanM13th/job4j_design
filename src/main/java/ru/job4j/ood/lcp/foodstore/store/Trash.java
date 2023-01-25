package ru.job4j.ood.lcp.foodstore.store;

import ru.job4j.ood.lcp.foodstore.model.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash extends AbstractStore {

    private final List<Food> products = new ArrayList<>();

    public List<Food> getProducts() {
        return products;
    }

    @Override
    public void add(Food food) {
        products.add(food);
    }
}
