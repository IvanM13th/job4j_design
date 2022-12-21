package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    public void parse(String[] args) {
        for (var s : args) {
            String[] kv = s.split("=", 2);
            validateString(kv);
            values.put(kv[0].substring(1), kv[1]);
        }
    }

    private void validateString(String[] str) {
        if (str.length != 2 || str[0].length() < 2 || str[1].isBlank()) {
            throw new IllegalArgumentException();
        }
        if (!str[0].startsWith("-")) {
            throw new IllegalArgumentException();
        }
    }

/*    public ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }*/

/*    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }*/
}
