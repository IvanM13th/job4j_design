package ru.job4j.ood.isp;

public class QuitAction implements ActionDelegate {
    @Override
    public void delegate() {
        System.out.println("Thank you for using this app!");
    }
}
