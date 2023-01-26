package ru.job4j.ood.lcp.foodstore.store;

import ru.job4j.ood.lcp.foodstore.model.Food;

public interface Store {

    boolean add(Food food);

    boolean delete(Food food);

    boolean replace(Food oldValue, Food newValue);

    Food findByName(String name);

}