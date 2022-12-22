package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zipFiles = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (var p : sources) {
                zipFiles.putNextEntry(new ZipEntry(p.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(p.toFile()))) {
                    zipFiles.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Wrong arguments");
        }
        ArgsName arg = ArgsName.of(args);
        Path directory = Path.of(arg.get("d"));
        if (!directory.toFile().exists() || !directory.toFile().isDirectory()) {
            throw new IllegalArgumentException();
        }
        if (!arg.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Not extension");
        }
        Zip zip = new Zip();
        Predicate<Path> cond = path -> !path.toFile().getName().endsWith(arg.get("e"));
        List<Path> l = Search.search(directory, cond);
        zip.packFiles(l, new File(arg.get("o")));
    }

}
