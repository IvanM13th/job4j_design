package ru.job4j.ood.lcp.foodstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lcp.foodstore.model.Banana;
import ru.job4j.ood.lcp.foodstore.model.Coffee;
import ru.job4j.ood.lcp.foodstore.model.Milk;
import ru.job4j.ood.lcp.foodstore.quality.ControlQuality;
import ru.job4j.ood.lcp.foodstore.utils.LocalDateTimeExpirationDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopTest {

    @Test
    public void whenTestShop() {
        LocalDateTimeExpirationDate localDateTimeExpirationDate = new LocalDateTimeExpirationDate();
        Shop shop = new Shop(localDateTimeExpirationDate);
        List<Store> stores = new ArrayList<>();
        stores.add(shop);
        ControlQuality controlQuality = new ControlQuality(stores);
        Milk milk = new Milk("Milk",
                LocalDateTime.now().minusDays(31),
                LocalDateTime.now().plusDays(5),
                200,
                10);
        Coffee coffee = new Coffee("Coffee",
                LocalDateTime.now().minusDays(31),
                LocalDateTime.now().plusDays(28),
                300,
                8);
        Banana banana = new Banana("Banana",
                LocalDateTime.now().minusDays(31),
                LocalDateTime.now().minusDays(1),
                300,
                8);

        controlQuality.distribute(milk);
        controlQuality.distribute(coffee);
        assertThat(shop.getProducts()).contains(milk);
        assertThat(shop.getProducts().get(0).getPrice()).isEqualTo(180);
        assertThat(shop.getProducts()).contains(coffee);
        assertThat(shop.getProducts()).doesNotContain(banana);

    }
}
