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

public class TrashTest {
    @Test
    public void whenTestTrash() {
        LocalDateTimeExpirationDate localDateTimeExpirationDate = new LocalDateTimeExpirationDate();
        Trash trash = new Trash(localDateTimeExpirationDate);
        List<Store> stores = new ArrayList<>();
        stores.add(trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        Milk milk = new Milk("Milk",
                LocalDateTime.now().minusDays(31),
                LocalDateTime.now().minusDays(1),
                200,
                10);
        Coffee coffee = new Coffee("Coffee",
                LocalDateTime.now().minusDays(31),
                LocalDateTime.now().plusDays(28),
                300,
                8);

        controlQuality.distribute(milk);
        controlQuality.distribute(coffee);
        assertThat(trash.getProducts()).contains(milk);
        assertThat(trash.getProducts()).doesNotContain(coffee);
    }
}
