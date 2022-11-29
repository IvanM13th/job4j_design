package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert sc = new SimpleConvert();
        List<String> list = sc.toList("Ivan", "Petr", "Alex", "Anton", "Fedor");
        assertThat(list).filteredOn(s -> s.length() < 5)
                .hasSize(3)
                .startsWith("Ivan")
                .containsAnyOf("Vasya", "Julia", "Petr")
                .doesNotContain("Sergei");
    }

    @Test
    void checkSet() {
        SimpleConvert sc = new SimpleConvert();
        Set<String> set = sc.toSet("Car", "Dog", "Cat", "Dog", "Cat", "Plane", "Train");
        assertThat(set).isNotNull()
                .hasSize(5)
                .containsAnyOf("Cat", "Plane", "Teleport")
                .allSatisfy(e -> assertThat(e).isLessThan("Wow"));
    }

    @Test
    void checkMap() {
        SimpleConvert sc = new SimpleConvert();
        Map<String, Integer> map = sc.toMap("Car", "Dog", "Cat", "Dog", "Cat", "Plane", "Train");
        assertThat(map).isNotNull()
                .hasSize(5)
                .containsKeys("Car", "Cat")
                .containsValues(1, 2)
                .containsEntry("Dog", 1);
    }
}
