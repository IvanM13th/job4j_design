package ru.job4j.exam;

import java.util.HashMap;
import java.util.Map;

public class GetArgs {

    private final Map<String, String> params = new HashMap<>();

    public static GetArgs getArgs(String[] args) {
        GetArgs getArgs = new GetArgs();
        getArgs.validateArgs(args);
        getArgs.parse(args);
        return getArgs;
    }

    private void validateArgs(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not enough parameters");
        }
        for (var line : args) {
            if ("-".startsWith(line) || !line.contains("=")) {
                throw new IllegalArgumentException("Incorrect arguments format");
            }
        }
    }

    private void validateLine(String[] line) {
        if (line.length != 2 || line[1].isBlank()) {
            throw new IllegalArgumentException("Wrong line format");
        }
    }

    private void parse(String[] args) {
        for (var l : args) {
            String[] kv = l.split("=", 2);
            validateLine(kv);
            params.put(kv[0].substring(1), kv[1]);
        }
    }

    public String getParam(String key) {
        if (!params.containsKey(key)) {
            throw new IllegalArgumentException("No such key");
        }
        return params.get(key);
    }
}
