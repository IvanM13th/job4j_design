package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        short sh = 15;
        int n = 30;
        long l = 12312312;
        float f = 2345f;
        double d = 23243.16;
        boolean bool = true;
        char ch = 'c';
        LOG.debug("\n byte - {},\n short - {},\n int - {},\n long - {},\n float - {},\n double - {},\n boolean - {},\n char - {}",
                b, sh, n, l, f, d, bool, ch);

    }
}
