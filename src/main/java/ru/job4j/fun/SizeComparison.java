package ru.job4j.fun;

import java.nio.file.Path;
import java.util.Comparator;

public class SizeComparison implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return Long.compare(Path.of(o2).toFile().length(), Path.of(o1).toFile().length());
    }
}
