package ru.job4j.ood.lcp.foodstore.quality;

import ru.job4j.ood.lcp.foodstore.model.Food;
import ru.job4j.ood.lcp.foodstore.store.Store;
import ru.job4j.ood.lcp.foodstore.utils.DateChecker;

import java.util.Map;
import java.util.function.Predicate;

public class ControlQuality {
    private final Map<String, Store> stores;
    private final DateChecker dateChecker;

    public ControlQuality(Map<String, Store> stores, DateChecker dateChecker) {
        this.stores = stores;
        this.dateChecker = dateChecker;
    }

    public void sendTo(Food food) {
        validate();
        boolean sent = false;
        double exp = getExpiry(food);
        if (!sent) {
            sent = sendToWarehouse(food, exp, a -> exp <= 25);
        }

        if (!sent) {
            sent = sendToShop(food, exp,
                    a -> exp > 25 && exp <= 100,
                    a -> exp > 75);
        }

        if (!sent) {
            sent = sendToTrash(food, exp, a -> exp > 100);
        }
    }

    private boolean sendToWarehouse(Food food, double expPercentage, Predicate<Double> predicate) {
        boolean rsl = false;
        if (predicate.test(expPercentage)) {
            stores.get("Warehouse").add(food);
            rsl = true;
        }
        return rsl;
    }

    private boolean sendToShop(Food food, double expPercentage, Predicate<Double> predicate, Predicate<Double> ifChangePrice) {
        boolean rsl = false;
        if (predicate.test(expPercentage)) {
            if (ifChangePrice.test(expPercentage)) {
                food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount() / 100));
            }
            stores.get("Shop").add(food);
            rsl = true;
        }
        return rsl;
    }

    private boolean sendToTrash(Food food, double expPercentage, Predicate<Double> predicate) {
        boolean rsl = false;
        if (predicate.test(expPercentage)) {
            stores.get("Trash").add(food);
            rsl = true;
        }
        return rsl;
    }

    private double getExpiry(Food food) {
        return dateChecker.getExpPercentage(food);
    }

    private void validate() {
        if (stores.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
