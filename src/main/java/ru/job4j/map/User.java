package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Ivan", 6, birthday);
        User user2 = new User("Ivan", 6, birthday);

        map.put(user1, new Object());
        map.put(user2, new Object());

        for (var s : map.keySet()) {
            int hc = System.identityHashCode(s);
            int bucket = hc & 15;
            System.out.println("Hashcode - " + hc + ", bucket - " + bucket);
        }
    }
}
