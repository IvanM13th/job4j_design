package ru.job4j.ood.isp;

public class RootPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        menu.forEach(i -> {
            int count = i.getNumber().split("\\.").length - 1;
            if  (count == 0) {
                System.out.println(i.getNumber() + i.getName());
            }
        });
    }
}
