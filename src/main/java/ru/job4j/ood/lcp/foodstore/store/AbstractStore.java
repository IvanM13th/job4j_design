package ru.job4j.ood.lcp.foodstore.store;

import ru.job4j.ood.lcp.foodstore.model.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    protected final List<Food> products = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        return products.add(food);
    }

    @Override
    public Food findByName(String name) {
        Food rsl = null;
        for (var p : products) {
            if (name.equals(p.getName())) {
                rsl = p;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean delete(Food food) {
        boolean rsl = false;
        Food finded = findByName(food.getName());
        if (finded != null) {
            products.remove(finded);
            rsl = true;
        }
        return rsl;
    }

    public boolean replace(Food oldValue, Food newValue) {
        boolean rsl = false;
        Food finded = findByName(oldValue.getName());
        if (finded != null) {
            products.set(products.indexOf(finded), newValue);
            rsl = true;
        }
        return rsl;
    }

    public List<Food> getProducts() {
        return products;
    }
}