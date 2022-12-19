package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysys {

    public void unavailable(String source, String target) {
        boolean down = false;
        String startTime = "";
        String endTime = "";
        List<String[]> list = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] s = line.split(" ");
                list.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                )
        )) {
            for (String[] s : list) {
                if (down && !"400".equals(s[0]) && !"500".equals(s[0])) {
                    down = false;
                    endTime = s[1];
                    out.println(startTime + ";" + endTime);
                }
                if (("400".equals(s[0]) || "500".equals(s[0])) && !down) {
                    down = true;
                    startTime = s[1];
                    endTime = startTime;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       Analysys analysys = new Analysys();
       analysys.unavailable("un1.csv", "analysys.csv");
    }
}
