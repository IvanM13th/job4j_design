package ru.job4j.ood.lcp.foodstore.quality;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lcp.foodstore.model.*;
import ru.job4j.ood.lcp.foodstore.store.Shop;
import ru.job4j.ood.lcp.foodstore.store.Store;
import ru.job4j.ood.lcp.foodstore.store.Trash;
import ru.job4j.ood.lcp.foodstore.store.Warehouse;
import ru.job4j.ood.lcp.foodstore.utils.LocalDateTimeExpirationDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ControlQualityTest {

    @Test
    public void whenDistributeProductsThenAddedToList() {
        Milk milk = new Milk("Milk",
                LocalDateTime.now().minusDays(31),
                LocalDateTime.now().plusDays(5),
                100,
                5);
        Coffee coffee = new Coffee("Coffee",
                LocalDateTime.now().minusDays(31),
                LocalDateTime.now().plusDays(300),
                300,
                8);
        Tea tea = new Tea("Tea",
                LocalDateTime.now().minusDays(31),
                LocalDateTime.now().plusDays(28),
                80,
                10);
        Banana banana = new Banana("Banana",
                LocalDateTime.now().minusDays(31),
                LocalDateTime.now().minusDays(5),
                80,
                10);
        LocalDateTimeExpirationDate localDateExpirationDate = new LocalDateTimeExpirationDate();
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