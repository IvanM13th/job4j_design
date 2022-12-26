package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        var file = Path.of(argsName.get("path")).toFile();
        var target = "stdout".equals(argsName.get("out"))
                ? Path.of("plug.csv")
                : Path.of(argsName.get("out"));
        Integer[] indexOf = getIndexes(argsName, file);

        try (PrintWriter print = new PrintWriter(target.toFile());
             Scanner scanner = new Scanner(file)) {
            StringBuilder string = new StringBuilder();
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                String[] spl = s.split(argsName.get("delimiter"));
                String[] inOrder = new String[spl.length];
                for (int i = 0; i < spl.length; i++) {
                    if (indexOf[i] >= 0) {
                        inOrder[indexOf[i]] = spl[i];
                    }
                }
                for (var str : inOrder) {
                    if (str != null) {
                        string.append(str).append(";");
                    }
                }
                String toPrint = string.substring(0, string.length() - 1);
                if (Path.of("plug.csv").equals(target)) {
                    System.out.println(toPrint);
                } else {
                    print.println(toPrint);
                }
                string = new StringBuilder();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Integer[] getIndexes(ArgsName argsName, File file) {
        Integer[] rsl = new Integer[0];
        try (Scanner scanner = new Scanner(file)) {
            String[] fields = scanner.nextLine().split(argsName.get("delimiter"));
            List<String> filters = List.of(argsName.get("filter").split(","));
            Integer[] indexes = new Integer[fields.length];
            for (int i = 0; i < fields.length; i++) {
                if (filters.contains(fields[i])) {
                    indexes[i] = filters.indexOf(fields[i]);
                } else {
                    indexes[i] = -1;
                }
            }
            rsl = indexes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private static void validate(String[] args, ArgsName argsName) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong arguments");
        }
        if (!Path.of(argsName.get("path")).toFile().exists()) {
            throw new IllegalArgumentException("File does not exists");
        }
        if (!";".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException("Wrong delimiter");
        }
        Path p = "stdout".equals(argsName.get("out"))
                ? Path.of("target.csv")
                : Path.of(argsName.get("out"));
        if (!p.toFile().exists()) {
            throw new IllegalArgumentException("Wrong output directory");
        }
        if (argsName.get("filter").isBlank()) {
            throw new IllegalArgumentException("Filters not found");
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        validate(args, argsName);
        handle(argsName);
    }
}
