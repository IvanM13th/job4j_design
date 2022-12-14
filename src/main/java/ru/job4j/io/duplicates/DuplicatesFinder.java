package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Map<FileProperty, List<Path>> map = findFiles(Path.of("F:\\music"));
        printDuplicates(map);
    }

    public static Map<FileProperty, List<Path>> findFiles(Path root) throws IOException {
        DuplicatesVisitor finder = new DuplicatesVisitor();
        Files.walkFileTree(root, finder);
        return finder.getFiles();
    }

    public static void printDuplicates(Map<FileProperty, List<Path>> dupl) {
        for (var k : dupl.keySet()) {
            List<Path> dir = dupl.get(k);
            if (dir.size() > 1) {
                System.out.printf("%s - %dKb\n", k.getName(), k.getSize() / 1000);
                for (var d : dir) {
                    System.out.println(d);
                }
            }
        }
    }
}
