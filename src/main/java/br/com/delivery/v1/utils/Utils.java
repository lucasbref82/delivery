package br.com.delivery.v1.utils;

import org.slf4j.helpers.MessageFormatter;

public class Utils {

    private Utils() {
    }

    public static String format(String format, Object... arguments) {
        return MessageFormatter.basicArrayFormat(format, arguments);
    }
}
