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
        if (files.containsKey(fileProp)) {
            List<Path> paths = files.get(fileProp);
            paths.add(file.toAbsolutePath());
        } else {
            files.put(fileProp, new ArrayList<Path>());
            List<Path> paths = files.get(fileProp);
            paths.add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }
}
