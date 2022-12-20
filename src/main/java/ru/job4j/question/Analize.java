package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int removed = 0;
        HashMap<Integer, User> map = new HashMap<>();
        for (var p : previous) {
            map.put(p.getId(), p);
        }

        for (var c : current) {
            if (!map.containsKey(c.getId())) {
                added++;
            }
            if (!map.containsValue(c) && map.containsKey(c.getId())) {
                changed++;
            }
            map.remove(c.getId());
        }
        removed = map.size();

        return new Info(added, changed, removed);
    }
}