package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
       Path start = Paths.get("F:\\download");
       search(start, path -> path.toFile().getName().endsWith(".mkv")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SeacrhFiles searcher = new SeacrhFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
