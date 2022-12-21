package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Path.of(args[0]);
        search(start, path -> path.toFile().getName().endsWith(".mkv")).forEach(System.out::println);

    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SeacrhFiles searcher = new SeacrhFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments in main method was found");
        }
        if (!".mkv".equals(args[1])) {
            throw new IllegalArgumentException("No extension type was set");
        }
    }
}
