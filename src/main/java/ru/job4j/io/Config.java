package ru.job4j.io;

import javax.management.StringValueExp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (!line.startsWith("#") && line.length() > 0) {
                    String[] pairs = line.split("=", 2);
                    validString(pairs, line);
                    values.put(pairs[0], pairs[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.getOrDefault(key, null);
    }

    public Map<String, String> getValues() {
        return values;
    }

    public boolean validString(String[] pairs, String line) {
        if (!line.contains("=")) {
            throw new IllegalArgumentException();
        }
        if (pairs.length == 0 || pairs[0].isEmpty() || pairs[1].isEmpty()) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {

    }
}
