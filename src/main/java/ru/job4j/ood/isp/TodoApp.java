package ru.job4j.ood.isp;

import java.util.Scanner;

public class TodoApp {
    private static final String NEW_TASK = "Add new task";
    private static final String NEW_CHILD_TASK = "Add new child task";
    private static final String SHOW_ALL_TASKS = "Show all tasks";
    private static final String QUIT = "Quit";
    private static final int ADD_NEW_TASK = 1;
    private static final int ADD_NEW_CHILD_TASK = 2;
    private static final int PRINT_ALL_TASKS = 3;

    private final Menu menu;
    private final Scanner scanner;
    private final MenuPrinter menuPrinter;

    public TodoApp(Menu menu, Scanner scanner, MenuPrinter menuPrinter) {
        this.menu = menu;
        this.scanner = scanner;
        this.menuPrinter = menuPrinter;
    }

    public static void main(String[] args) {

        Scanner menuScanner = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new RootPrinter();
        TodoApp todoApp = new TodoApp(menu, menuScanner, printer);

        menu.add(Menu.ROOT, NEW_TASK, new AddNewTaskAction(menu, menuScanner));
        menu.add(Menu.ROOT, NEW_CHILD_TASK, new AddNewChildTaskAction(menu, menuScanner));
        menu.add(Menu.ROOT, SHOW_ALL_TASKS, new PrintTasksAction(menu));
        menu.add(Menu.ROOT, QUIT, new QuitAction());

        todoApp.init();
    }

    private void init() {
        boolean run = true;
        while (run) {
            menuPrinter.print(menu);
            System.out.println("Pick action: ");
            int i = Integer.parseInt(scanner.nextLine());
            if (i == ADD_NEW_TASK) {
                executeChoice(NEW_TASK);
            } else if (i == ADD_NEW_CHILD_TASK) {
                executeChoice(NEW_CHILD_TASK);
            } else if (i == PRINT_ALL_TASKS) {
                executeChoice(SHOW_ALL_TASKS);
            } else {
                executeChoice(QUIT);
                run = false;
            }
        }
    }

    private void executeChoice(String choice) {
        menu.select(choice).get().getActionDelegate().delegate();
    }
}
