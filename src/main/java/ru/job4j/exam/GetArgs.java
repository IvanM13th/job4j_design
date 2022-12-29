package ru.job4j.exam;

import java.util.HashMap;
import java.util.Map;

public class GetArgs {

    private final Map<String, String> params = new HashMap<>();

    public static GetArgs getArgs(String[] args) {
        GetArgs getArgs = new GetArgs();
        getArgs.parse(args);
        return getArgs;
    }

    private void validateLine(String[] args) {
        for (var line : args) {
            if (args.length != 2 || args[0].isBlank() || args[1].isBlank()) {
                throw new IllegalArgumentException("Key or value is empty");
            }
            if (!args[0].startsWith("-")) {
                throw new IllegalArgumentException("Incorrect arguments format");
            }
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
            throw new IllegalArgumentException("Key is not found");
        }
        return params.get(key);
    }
}
