package ru.job4j.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        var file = Path.of(argsName.get("path")).toFile();
        var target = Path.of(argsName.get("out"));
        try (PrintWriter print = new PrintWriter(target.toFile());
             Scanner scanner = new Scanner(file)) {
            boolean[] filters = getFilters(argsName, file);
            StringBuilder string = new StringBuilder();
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                String[] spl = s.split(argsName.get("delimiter"));
                for (int i = 0; i < spl.length; i++) {
                    if (filters[i]) {
                        string.append(spl[i]).append(";");
                    }
                }
                print.println(string.substring(0, string.length() - 1));
                string = new StringBuilder();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static boolean[] getFilters(ArgsName argsName, File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String firstLine = scanner.nextLine();
        String[] fields = firstLine.split(argsName.get("delimiter"));
        boolean[] filters = new boolean[fields.length];
        for (int i = 0; i < filters.length; i++) {
            filters[i] = argsName.get("filter").contains(fields[i]);
        }
        return filters;
    }
}
