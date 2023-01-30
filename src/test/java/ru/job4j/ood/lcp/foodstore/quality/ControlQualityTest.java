package ru.job4j.ood.lcp.foodstore.quality;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lcp.foodstore.model.*;
import ru.job4j.ood.lcp.foodstore.store.Shop;
import ru.job4j.ood.lcp.foodstore.store.Store;
import ru.job4j.ood.lcp.foodstore.store.Trash;
import ru.job4j.ood.lcp.foodstore.store.Warehouse;
import ru.job4j.ood.lcp.foodstore.utils.LocalDateExpirationDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ControlQualityTest {

    @Test
    public void whenDistributeProductsThenAddedToList() {
        Milk milk = new Milk("Milk",
                LocalDateTime.of(2023, Month.JANUARY, 1, 1, 1),
                LocalDateTime.of(2023, Month.FEBRUARY, 5, 1, 1),
                100,
                5);
        Coffee coffee = new Coffee("Coffee",
                LocalDateTime.of(2023, Month.JANUARY, 1, 1, 1),
                LocalDateTime.of(2023, Month.DECEMBER, 31, 1, 1),
                300,
                8);
        Tea tea = new Tea("Tea",
                LocalDateTime.of(2023, Month.JANUARY, 1, 1, 1),
                LocalDateTime.of(2023, Month.FEBRUARY, 28, 1, 1),
                80,
                10);
        Banana banana = new Banana("Banana",
                LocalDateTime.of(2023, Month.JANUARY, 1, 1, 1),
                LocalDateTime.of(2023, Month.JANUARY, 24, 1, 1),
                80,
                10);
        LocalDateExpirationDate localDateExpirationDate = new LocalDateExpirationDate();
        Shop shop = new Shop(localDateExpirationDate);
        Warehouse warehouse = new Warehouse(localDateExpirationDate);
        Trash trash = new Trash(localDateExpirationDate);
        List<Store> stores = new ArrayList<>();
        stores.add(shop);
        stores.add(warehouse);
        stores.add(trash);
        ControlQuality controlQuality = new ControlQuality(stores);

        controlQuality.distribute(milk);
        controlQuality.distribute(coffee);
        controlQuality.distribute(tea);
        controlQuality.distribute(banana);

        assertThat(shop.getProducts()).contains(milk);
        assertThat(shop.getProducts().get(0).getPrice()).isEqualTo(95);
        assertThat(shop.getProducts()).contains(tea);
        assertThat(warehouse.getProducts()).contains(coffee);
        assertThat(trash.getProducts()).contains(banana);
    }
}