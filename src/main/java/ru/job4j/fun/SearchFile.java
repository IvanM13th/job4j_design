package ru.job4j.fun;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
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
        String fileName = file.toFile().getAbsolutePath();
        if (filter.test(fileName)) {
            files.add(file.toFile().getAbsolutePath());
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        if (exc instanceof AccessDeniedException) {
            return FileVisitResult.SKIP_SUBTREE;
        }
        return super.visitFileFailed(file, exc);
    }
}
