package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        List<String> logs = new ArrayList<>();
        Random rng = new Random();
        Scanner in = new Scanner(System.in);
        System.out.println("Привет, я консольный бот, давай поболтаем!");
        String humanSays;
        String botSays;
        String bye = "Ну, пока!";
        boolean silence = false;

        do {
            humanSays = in.nextLine();
            logs.add(humanSays);
            if (humanSays.equals(STOP)) {
                silence = true;
            } else if (humanSays.equals(CONTINUE)) {
                silence = false;
            }

            if (!silence && !humanSays.equals(OUT)) {
                botSays = phrases.get(rng.nextInt(phrases.size()));
                System.out.println(botSays);
                logs.add(botSays);
            }

        } while (!humanSays.equals(OUT));
        System.out.println(bye);
        logs.add(bye);
        saveLog(logs);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("botAns.txt"))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                phrases.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> args) {
        try (PrintWriter print = new PrintWriter("botLog.txt")) {
            for (var l : args) {
                print.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong arguments");
        }
        File file = new File(args[1]);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found");
        }
        for (var d : args) {
            if (!d.endsWith(".txt")) {
                throw new IllegalArgumentException("Wrong file format");
            }
        }

        ConsoleChat cc = new ConsoleChat(args[0], args[1]);
        cc.run();
    }
}
