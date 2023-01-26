package ru.job4j.ood.lcp.foodstore.quality;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lcp.foodstore.model.Food;
import ru.job4j.ood.lcp.foodstore.store.Shop;
import ru.job4j.ood.lcp.foodstore.store.Store;
import ru.job4j.ood.lcp.foodstore.store.Trash;
import ru.job4j.ood.lcp.foodstore.store.Warehouse;
import ru.job4j.ood.lcp.foodstore.utils.DateChecker;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ControlQualityTest {

    @Test
    public void whenFirstTest() {
        Food milk = new Food("Milk",
                LocalDateTime.of(2023, Month.JANUARY, 1, 1, 1),
                LocalDateTime.of(2023, Month.JANUARY, 31, 1, 1),
                100,
                5);
        Food coffee = new Food("Coffee",
                LocalDateTime.of(2023, Month.JANUARY, 1, 1, 1),
                LocalDateTime.of(2023, Month.DECEMBER, 31, 1, 1),
                300,
                8);
        Food tea = new Food("Tea",
                LocalDateTime.of(2023, Month.JANUARY, 1, 1, 1),
                LocalDateTime.of(2023, Month.FEBRUARY, 28, 1, 1),
                80,
                10);
        Food banana = new Food("Banana",
                LocalDateTime.of(2023, Month.JANUARY, 1, 1, 1),
                LocalDateTime.of(2023, Month.JANUARY, 24, 1, 1),
                80,
                10);
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        Map<String, Store> stores = new HashMap<>();
        stores.put("Shop", shop);
        stores.put("Warehouse", warehouse);
        stores.put("Trash", trash);
        DateChecker dateChecker = new DateChecker();
        ControlQuality controlQuality = new ControlQuality(stores, dateChecker);

        controlQuality.sendTo(milk);
        controlQuality.sendTo(coffee);
        controlQuality.sendTo(tea);
        controlQuality.sendTo(banana);

        assertThat(shop.getProducts()).contains(milk);
        assertThat(shop.getProducts().get(0).getPrice()).isEqualTo(95);
        assertThat(shop.getProducts()).contains(tea);
        assertThat(warehouse.getProducts()).contains(coffee);
        assertThat(trash.getProducts()).contains(banana);
    }
}
