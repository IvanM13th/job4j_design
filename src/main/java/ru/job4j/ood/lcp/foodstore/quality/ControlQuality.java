package ru.job4j.ood.lcp.foodstore.quality;

import ru.job4j.ood.lcp.foodstore.model.Food;
import ru.job4j.ood.lcp.foodstore.store.Store;
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

    private void validate() {
        if (stores.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}