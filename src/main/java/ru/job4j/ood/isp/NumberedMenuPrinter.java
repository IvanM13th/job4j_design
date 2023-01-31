package ru.job4j.ood.isp;

import java.util.function.Consumer;

public class NumberedMenuPrinter implements MenuPrinter {

    private final Consumer<String> output;

    public NumberedMenuPrinter(Consumer<String> output) {
        this.output = output;
    }

    @Override
    public void print(Menu menu) {
        menu.forEach(i -> {
            int count = i.getNumber().split("\\.").length - 1;
            String text = "";
            for (int j = 0; j < count; j++) {
                text = String.format("%s%s", text, "--");
            }
            println(text + i.getNumber() + i.getName());
        });
    }

    private void println(String text) {
        output.accept(text);
    }
}
