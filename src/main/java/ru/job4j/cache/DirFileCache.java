package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        Path path = Path.of(key).toAbsolutePath();
        StringBuilder builder = new StringBuilder();
        try {
            Files.lines(path).forEach(line -> {
                builder.append(line).append(" ");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void main(String[] args) throws IOException {
        String dir = "src/main/java/ru/job4j/gc/leak/files/names.txt";

        DirFileCache dirFileCache = new DirFileCache(dir);

        dirFileCache.put(dir, dirFileCache.load(dir));

        System.out.println(dirFileCache.load(dir));

        String soft = dirFileCache.get("src/main/java/ru/job4j/gc/leak/files/names.txt");

        System.out.println(soft);
    }
}
