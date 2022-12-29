package ru.job4j.exam;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFile extends SimpleFileVisitor<Path> {
    private String type;
    private String name;

    private final List<String> files = new ArrayList<>();

    public SearchFile(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public List<String> getFiles() {
        return files;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.toFile().getName();
        if ("mask".equals(type)) {
            if (fileName.contains(name)) {
                files.add(fileName);
            }
        } else if ("name".equals(type)) {
            if (name.contains(fileName.substring(0, fileName.length() - 4))) {
                files.add(fileName);
            }
        } else if ("regex".equals(type)) {
            Pattern p = Pattern.compile(name);
            Matcher m = p.matcher(fileName);
            if (m.find()) {
                files.add(fileName);
            }
        }
        return CONTINUE;
    }
}
