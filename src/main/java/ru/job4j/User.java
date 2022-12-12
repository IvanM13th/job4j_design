package ru.job4j;

import java.util.Calendar;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Ivan", 6, birthday);
        User user2 = new User("Ivan", 6, birthday);
    }
}