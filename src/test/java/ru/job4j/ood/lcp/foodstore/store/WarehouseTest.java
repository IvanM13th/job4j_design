package ru.job4j.ood.lcp.foodstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lcp.foodstore.model.Coffee;
import ru.job4j.ood.lcp.foodstore.model.Milk;
import ru.job4j.ood.lcp.foodstore.quality.ControlQuality;
import ru.job4j.ood.lcp.foodstore.utils.LocalDateTimeExpirationDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WarehouseTest {
    @Test
    public void whenTestWarehouse() {
        LocalDateTimeExpirationDate localDateTimeExpirationDate = new LocalDateTimeExpirationDate();
        Warehouse warehouse = new Warehouse(localDateTimeExpirationDate);
        List<Store> stores = new ArrayList<>();
        stores.add(warehouse);
        ControlQuality controlQuality = new ControlQuality(stores);
        Milk milk = new Milk("Milk",
                LocalDateTime.now().minusDays(31),
                LocalDateTime.now().plusDays(250),
                200,
                10);
        Coffee coffee = new Coffee("Coffee",
                LocalDateTime.now().minusDays(31),
                LocalDateTime.now().plusDays(28),
                300,
                8);

        controlQuality.distribute(milk);
        controlQuality.distribute(coffee);
        assertThat(warehouse.getProducts()).contains(milk);
        assertThat(warehouse.getProducts()).doesNotContain(coffee);
    }
}
