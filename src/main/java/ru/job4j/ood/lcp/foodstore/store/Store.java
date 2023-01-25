package ru.job4j.ood.lcp.foodstore.store;

import ru.job4j.ood.lcp.foodstore.model.Food;

import java.util.List;

public interface Store {

    void add(Food food);

    List<Food> getProducts();
}
