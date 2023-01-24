package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Disabled
public class StringGeneratorTest {

    /**
     * Тест проверяет, что, в случае ввода всех коректных данных,
     * мы получим корректную строку.
     */
    @Test
    public void whenCorrectInputThenCorrectString() {
        String template = "I am ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Ivan");
        args.put("subject", "you");
        StringGenerator stringGenerator = new StringGenerator();
        String rsl = stringGenerator.produce(template, args);
        assertThat(rsl).isEqualTo("I am Ivan, Who are you?");
    }

    /**
     * Тест проверяет, что в случае отстуствия ключа в мапе
     * будет выброшено исключение
     */
    @Test
    public void whenNoSuchKeyThenException() {
        String template = "I am ${nam}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Ivan");
        args.put("subject", "you");
        StringGenerator stringGenerator = new StringGenerator();
        assertThatThrownBy(() -> stringGenerator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Тест проверяет, что в случае отсутствия значения по ключу,
     * будет выброшено исключение
     */
    @Test
    public void whenNoValueForTheKeyThenException() {
        String template = "I am ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Ivan");
        args.put("subject", null);
        StringGenerator stringGenerator = new StringGenerator();
        assertThatThrownBy(() -> stringGenerator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Тест проверяет, что в случае пустой мапы
     * будет выброшено исключение
     */
    @Test
    public void whenMapIsEmptyThenException() {
        String template = "I am ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        StringGenerator stringGenerator = new StringGenerator();
        assertThatThrownBy(() -> stringGenerator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * В задании указано, что программа долждна учитывать, что в карте есть лишние ключи.
     * Предположим, что в методе мы сделали проверку, что size() мапы должен быть равен
     * количеству ключей в шаблоне, это и проверяем в тесте.
     * Если числа не равны, кидаем исключение.
     */
    @Test
    public void whenExtraKeysThenException() {
        String template = "I am ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Ivan");
        args.put("subject", "you");
        args.put("subj", "me");
        StringGenerator stringGenerator = new StringGenerator();
        assertThatThrownBy(() -> stringGenerator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
