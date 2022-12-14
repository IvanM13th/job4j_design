package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    public Map<FileProperty, List<Path>> getFiles() {
        return files;
    }

    public DuplicatesVisitor() {
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(file.toFile().length(), file.toFile().getName());
        files.putIfAbsent(fileProp, new ArrayList<Path>());
        files.get(fileProp).add(file.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }
}
