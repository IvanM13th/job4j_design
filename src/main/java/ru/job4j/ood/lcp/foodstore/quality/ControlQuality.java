package ru.job4j.ood.lcp.foodstore.quality;

import ru.job4j.ood.lcp.foodstore.model.Food;
import ru.job4j.ood.lcp.foodstore.store.Store;
import ru.job4j.ood.lcp.foodstore.utils.DateChecker;

import java.util.Map;

public class ControlQuality {
    private final Map<String, Store> stores;
    private final DateChecker dateChecker;

    public ControlQuality(Map<String, Store> stores, DateChecker dateChecker) {
        this.stores = stores;
        this.dateChecker = dateChecker;
    }

    public void sendToStore(Food food) {
        validate();
        double expPercentage = dateChecker.getExpPercentage(food);
        if (expPercentage <= 25) {
            stores.get("Warehouse").add(food);
        } else if (expPercentage > 25
                && expPercentage <= 75) {
            stores.get("Shop").add(food);
        } else if (expPercentage > 75
                && expPercentage <= 100) {
                food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount() / 100));
            stores.get("Shop").add(food);
        } else {
            stores.get("Trash").add(food);
        }
    }

    private void validate() {
        if (stores.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
