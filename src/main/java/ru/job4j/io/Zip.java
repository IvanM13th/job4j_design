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
        if (args.length != 3 || !args[0].startsWith("-d") || !args[1].startsWith("-e") || !args[2].startsWith("-o")) {
            throw new IllegalArgumentException("Wrong arguments");
        }
        ArgsName arg = new ArgsName();
        arg.parse(args);
        Path directory = Path.of(arg.get("d"));
        if (!directory.toFile().exists()) {
            throw new IllegalArgumentException();
        }
        Zip zip = new Zip();
        Predicate<Path> cond = path -> !path.toFile().getName().endsWith(arg.get("e"));
        Search searcher = new Search();
        List<Path> l = searcher.search(directory, cond);
        zip.packFiles(l, new File(arg.get("o")));
    }

}
