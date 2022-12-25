package ru.job4j.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws FileNotFoundException {
        var file = Path.of(argsName.get("path")).toFile();
        var target = Path.of(argsName.get("out"));
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
                print.println(string.substring(0, string.length() - 1));
                string = new StringBuilder();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Integer[] getIndexes(ArgsName argsName, File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
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
        return indexes;
    }
}
