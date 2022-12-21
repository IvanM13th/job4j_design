package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        validateKey(key);
        return values.get(key);
    }

    private void parse(String[] args) {
        for (var s : args) {
            String[] kv = s.split("=", 2);
            validateString(kv);
            values.put(kv[0].substring(1), kv[1]);
        }
    }

    private void validateString(String[] str) {
        if (str.length != 2 || str[0].length() < 2 || "".equals(str[1])) {
            throw new IllegalArgumentException();
        }
    }

    private void validateKey(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        for (var s : args) {
            if (!"-".equals(String.valueOf(s.charAt(0)))) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static ArgsName of(String[] args) {
        validateArgs(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
