package ru.job4j.question;

import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int removed = 0;
        int count = 0;
        for (User c : current) {
            if (!previous.contains(c)) {
                added++;
            }
            for (var p : previous) {
                if (c.getId() == p.getId() && !c.getName().equals(p.getName())) {
                    changed++;
                }
                if (!current.contains(p) && count < previous.size()) {
                    removed++;
                }
                count++;
            }
        }
        return new Info(added, changed, removed);
    }
}

