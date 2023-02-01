package ru.job4j.ood.lcp.foodstore.quality;

import ru.job4j.ood.lcp.foodstore.model.Food;
import ru.job4j.ood.lcp.foodstore.store.Store;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribute(Food food) {
        validate();
        for (var store : stores) {
            store.add(food);
        }
    }

    public void resort() {
        List<Food> products = getAllProducts();
        for (var store : stores) {
            store.clear();
        }
        for (var food : products) {
            distribute(food);
        }
    }

    private void validate() {
        if (stores.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private List<Food> getAllProducts() {
        List<Food> rsl = new ArrayList<>();
        for (var store : stores) {
            rsl.addAll(store.getProducts());
        }
        return rsl;
    }
}