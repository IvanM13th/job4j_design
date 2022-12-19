package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
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

    public static void main(String[] args) {
        Logfilter lofFilter = new Logfilter();
        List<String> log = lofFilter.filter("log.txt");
        System.out.println(log.size());
        System.out.println(log);
    }
}
