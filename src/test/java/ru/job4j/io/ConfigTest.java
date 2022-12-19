package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenFileIsCorrect() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"))
                .isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
    }

    @Test
    void whenHasCommentsAndEmptyLines() {
        String path = "./data/withCommentsAndEmptyLines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.getValues().size()).isEqualTo(4);
    }

    @Test
    void whenHasNoKey() {
        String path = "./data/whenNoKey.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNoValue() {
        String path = "./data/whenNoValue.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNoValueAndNoKey() {
        String path = "./data/NoValueAndNoKey.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .hasMessageContaining("has no key or has no value")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMoreThanOneEqualSign() {
        String path = "./data/whenTwoEqualSigns.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"))
                .isEqualTo("org.hibernate.dialect.PostgreSQLDialect=");
        assertThat(config.value("hibernate.connection.driver_class"))
                .isEqualTo("org.postgresql.Driver=1");
        assertThat(config.value("hibernate.connection.username"))
                .isEqualTo("postgres==");
        assertThat(config.value("hibernate.connection.password"))
                .isEqualTo("password===34");
    }
}