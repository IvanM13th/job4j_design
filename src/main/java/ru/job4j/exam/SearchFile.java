package ru.job4j.exam;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFile extends SimpleFileVisitor<Path> {

    private final Predicate<String> filter;

    private final List<String> files = new ArrayList<>();

    public SearchFile(Predicate<String> filter) {
        this.filter = filter;
    }

    public List<String> getFiles() {
        return files;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.toFile().getName();
        if (filter.test(fileName)) {
            files.add(file.toFile().getAbsolutePath());
        }
        return CONTINUE;
    }
}
