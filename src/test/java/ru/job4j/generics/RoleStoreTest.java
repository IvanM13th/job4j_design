package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleStoreTest {

    @Test
    void whenAddNewRole() {
        RoleStore rs = new RoleStore();
        rs.add(new Role("01", "Assistant"));
        Role rsl = rs.findById("01");
        assertThat(rsl.getRole()).isEqualTo("Assistant");
    }

    @Test
    void whenAddThenRemove() {
        RoleStore rs = new RoleStore();
        rs.add(new Role("01", "Assistant"));
        assertThat(rs.delete("01")).isTrue();
    }

    @Test
    void whenChangeRoleIsTrue() {
        RoleStore rs = new RoleStore();
        rs.add(new Role("1", "Manager"));
        rs.replace("1", new Role("1", "TopManager"));
        assertThat(rs.findById("1").getRole()).isEqualTo("TopManager");
    }

    @Test
    void whenRemoveFails() {
        RoleStore rs = new RoleStore();
        rs.add(new Role("1", "Manager"));
        boolean rsl = rs.delete("2");
        assertThat(rsl).isFalse();
    }

    @Test
    void whenAddThenFindSuccess() {
        RoleStore rs = new RoleStore();
        rs.add(new Role("1", "Salesperson"));
        Role rsl = rs.findById("1");
        assertThat(rsl.getRole()).isEqualTo("Salesperson");
    }

}
