package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema3DTest {
    /**
     * В тесте проверяем, что при покупке билета пользователем,
     * он получает свой билет, то есть метод buy вернет нвой объект ticket
     */
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    /**
     * Тест проверяет, что при добавлении нового сеанса,
     * он отобразится в списке всех сеансов
     */
    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(ses -> true);
        assertThat(sessions).contains(session);
    }

    /**
     * Тест проверяет, что при вводе пользователем некорретных данных,
     * например, ряда, будет выброшено исключение.
     */
    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Добавлено.
     * Тест проверяет, что в случае, если сеанс не найден в списке сеансов,
     * пользователю будет показана ошибка
     */
    @Test
    public void whenSessionNotFoundThenGetException() {
        Cinema cinema = new Cinema3D();
        assertThatThrownBy(() -> cinema.find(ses -> false))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Добавлено.
     * Тест проверяет, что при вводе пользователем некорретных данных,
     * например, места в ряду, будет выброшено исключение.
     */
    @Test
    public void whenBuyOnInvalidColumnThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, 1, -1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Добавлено.
     * Тест проверяет, что если сеанс не был добавлен в список, то его там нет.
     * Вообще, сделал бы так, чтобы метод add() возвращал boolean,
     * и в данном тесте бы проверил, какое значение вернул данный метод.
     * Но тк реализации классов у нас нет, то оставлю так.
     */
    @Test
    public void whenAddSessionAndFail() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(ses -> false);
        assertThat(sessions).doesNotContain(session);
    }

    /**
     * Тест проверяет, что в случае попытки продать билет на уже занятое место,
     * будет выброшено исключение
     */
    @Test
    public void whenTryBuyBusySeat() {
        Cinema cinema = new Cinema3D();
        Account account = new AccountCinema();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Тест проверяет, что нельзя купить билет на несуществующую
     * или некорректно введенную дату
     */
    @Test
    public void whenBuyTicketAndInvalidDate() {
        Cinema cinema = new Cinema3D();
        Account account = new AccountCinema();
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
