package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator extends DirFileCache {

    private String dir;

    public Emulator(String cachingDir) {
        super(cachingDir);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите директорию для кэширования");
        String link = scanner.nextLine();

        Emulator emul = new Emulator(link);

        System.out.println("нажмите любую клавишу, чтобы загрузить содержимое файла в кэш");
        String str = scanner.nextLine();
        emul.put(link, emul.load(link));

        System.out.println("нажмите любую клавишу, чтобы получить содержимое файла");
        String str1 = scanner.nextLine();
        System.out.println(emul.get(link));
    }
}
