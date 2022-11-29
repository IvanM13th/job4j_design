package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void validateEqualitySymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "Ivan";
        assertThatThrownBy(() -> nameLoad.validate(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name)
                .hasMessageContaining("Ivan");
    }

    @Test
    void validateStartsWith() {
        NameLoad nameLoad = new NameLoad();
        String name = "=";
        assertThatThrownBy(() -> nameLoad.validate(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("key")
                .hasMessageContaining(name);
    }

    @Test
    void noParse() {
        NameLoad nl = new NameLoad();
        assertThatThrownBy(nl::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty");
    }
}