package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {

    private static final int SET_FILE = 1;
    private static final int PUT_TO_CASH = 2;
    private static final int GET_FROM_CASH = 3;
    private static final String SELECT = "Выберите меню";
    private static final String EXIT = "Конец работы";
    private static final String MENU = """
                Введите 1, чтобы указать файл для кэширования.
                Введите 2, чтобы добавить данные в кэш.
                Введите 3, чтобы получить данне из кэша.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        boolean run = true;
        String fileName = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите директорию");
        String directory = scanner.nextLine();
        DirFileCache dirFileCache = new DirFileCache(directory);
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == SET_FILE) {
                System.out.println("Введите название файла");
                fileName = scanner.nextLine();
                System.out.println("Имя файла:" + fileName);

            } else if (choice == PUT_TO_CASH) {
                System.out.println("Добавляю данные в кэш");
                if (!"".equals(fileName)) {
                    dirFileCache.get(fileName);
                }
            } else if (choice == GET_FROM_CASH) {
                String rsl = dirFileCache.get(fileName);
                System.out.println(rsl);
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}