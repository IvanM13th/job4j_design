package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (var line : lines) {
                String s = Integer.parseInt(line) % 2 == 0
                        ? "Число " + line + " четное"
                        : "Число " + line + " нечетное";
                System.out.println(s);
                }
            } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }
