package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Logfilter {
    public List<String> filter(String file) {
        List<String> errors = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] errorCode = line.split(" ");
                if ("404".equals(errorCode[errorCode.length - 2])) {
                    errors.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errors;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                )
        )) {
            for (var s : log) {
                out.printf("%s%n", s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Logfilter lofFilter = new Logfilter();
        List<String> log = lofFilter.filter("log.txt");
        save(log, "404.txt");
        System.out.println(log);
    }
}
