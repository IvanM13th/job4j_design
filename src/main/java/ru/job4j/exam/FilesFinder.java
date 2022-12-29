package ru.job4j.exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FilesFinder {

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        GetArgs getArgs = GetArgs.getArgs(args);
        Path path = Path.of(getArgs.getParam("d"));
        Path out = Path.of(getArgs.getParam("o"));
        String name = getArgs.getParam("n");
        String type = getArgs.getParam("t");

        validateParams(path, out, type);
        Predicate<String> filter = getPredicate(type, name);

        List<String> f = files(path, filter);
        logWriter(f, out.toString());
    }

    private static List<String> files(Path path, Predicate<String> filter) throws IOException {
        SearchFile searcher = new SearchFile(filter);
        Files.walkFileTree(path, searcher);
        return searcher.getFiles();
    }

    private static void logWriter(List<String> files, String file) {
        try (PrintWriter print = new PrintWriter(file)) {
            for (var l : files) {
                print.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateParams(Path dir, Path log, String type) {
        if (!dir.toFile().exists() || !log.toFile().exists()) {
            throw new IllegalArgumentException("Files do not exist");
        }
        if (!dir.toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory not found");
        }
        if (!"mask".equals(type) && !"name".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException("Illegal types");
        }
    }

    private static Predicate getPredicate(String type, String name) {
        Predicate<String> rsl = null;
        if ("name".equals(type)) {
            rsl = name::equals;
        } else if ("mask".equals(type)) {
            rsl = s -> s.contains(name);
        } else if ("regex".equals(type)) {
            Pattern p = Pattern.compile(name);
            rsl = p.asPredicate();
        }
        return rsl;
    }
}

