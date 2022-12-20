package ru.job4j.io;

import java.io.*;

public class Analysys {

    public void unavailable(String source, String target) {
        boolean down = false;
        String startTime = "";
        String endTime = "";
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter print = new PrintWriter(target)) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] s = line.split(" ");

                if (down && !"400".equals(s[0]) && !"500".equals(s[0])) {
                    down = false;
                    endTime = s[1];
                    print.printf("%s;%s\n", startTime, endTime);
                }

                if (("400".equals(s[0]) || "500".equals(s[0])) && !down) {
                    down = true;
                    startTime = s[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Analysys analysys = new Analysys();
        analysys.unavailable("un1.csv", "analysys.csv");
    }
}

/*
    PrintWriter print = new PrintWriter(
            new BufferedOutputStream(
                    new FileOutputStream(target)
            ));
    BufferedReader in = new BufferedReader(new FileReader(source));
        for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] s = line.split(" ");

                if (down && !"400".equals(s[0]) && !"500".equals(s[0])) {
                down = false;
                endTime = s[1];
                print.write(startTime + ";" + endTime);
                }
                if (("400".equals(s[0]) || "500".equals(s[0])) && !down) {
                down = true;
                startTime = s[1];
                endTime = startTime;
                }
                }*/
