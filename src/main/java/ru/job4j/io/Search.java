package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw  new IllegalArgumentException("Root folder is null");
        }
        if (!Path.of(args[0]).toFile().exists()) {
            throw new IllegalArgumentException("Folder doesn't exist");
        }
        if (!Path.of(args[0]).toFile().isDirectory()) {
            throw new IllegalArgumentException("Not a directory");
        }
        Path start = Path.of(args[0]);
        search(start, path -> path.toFile().getName().endsWith(".mkv")).forEach(System.out::println);

    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SeacrhFiles searcher = new SeacrhFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
