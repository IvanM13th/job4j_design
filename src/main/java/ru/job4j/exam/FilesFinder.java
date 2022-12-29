package ru.job4j.exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FilesFinder {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not found");
        }
        GetArgs getArgs = GetArgs.getArgs(args);
        Path p = Path.of(getArgs.getParam("d"));
        Path out = Path.of(getArgs.getParam("o"));
        if (!p.toFile().exists() || !out.toFile().exists()) {
            throw new IllegalArgumentException("Files do not exist");
        }
        if (!p.toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory not found");
        }
        String type = getArgs.getParam("t");
        if (!"mask".equals(type) && !"name".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException("Illegal types");
        }
        String name = getArgs.getParam("n");
        List<String> f = files(p, name, type);
        logWriter(f, out.toString());
    }

    private static List<String> files(Path path, String name, String type) throws IOException {
        SearchFile searcher = new SearchFile(type, name);
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
}
