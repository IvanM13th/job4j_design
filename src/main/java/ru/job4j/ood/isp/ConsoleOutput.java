package ru.job4j.ood.isp;

public class ConsoleOutput implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }
}