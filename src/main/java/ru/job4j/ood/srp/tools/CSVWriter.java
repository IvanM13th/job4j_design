package ru.job4j.ood.srp.tools;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVWriter implements FileWriter {

    @Override
    public void saveToFile(File file, String data) {
        try (PrintWriter print = new PrintWriter(file)) {
            print.print(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
