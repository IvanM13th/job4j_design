package ru.job4j.ood.isp;

public class PrintTasksAction implements ActionDelegate {

    private final Menu menu;

    public PrintTasksAction(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void delegate() {
        menu.forEach(i -> {
            int count = i.getNumber().split("\\.").length;
            if (count > 1) {
                String num = i.getNumber().substring(2);
                System.out.println(num + i.getName());
            }
        });
    }
}
