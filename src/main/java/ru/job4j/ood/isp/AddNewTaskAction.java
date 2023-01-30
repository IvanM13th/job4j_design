package ru.job4j.ood.isp;

import java.util.Scanner;

public class AddNewTaskAction implements ActionDelegate {
    private final Menu menu;
    private final Scanner scanner;
    private final static String TASKS = "Show all tasks";

    private static final ActionDelegate ADD = () -> System.out.println("Task has been added");

    public AddNewTaskAction(Menu menu, Scanner scanner) {
        this.menu = menu;
        this.scanner = scanner;
    }

    @Override
    public void delegate() {
        System.out.println("Enter task name");
        String taskName  = scanner.nextLine();
        menu.add(TASKS, taskName, ADD);
    }
}
