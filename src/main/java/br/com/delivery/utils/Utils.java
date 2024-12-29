package br.com.delivery.utils;

import jakarta.persistence.Id;
import lombok.NonNull;
import org.slf4j.helpers.MessageFormatter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    private Utils() {

    }

    public static String format(String format, Object... arguments) {
        return MessageFormatter.basicArrayFormat(format, arguments);
    }

    public static String gerarStringRandomica(int lenght) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(lenght);
        for (int i = 0; i < lenght; i++) {
            int randomAscii;
            int randomCategory = random.nextInt(3);
            randomAscii = switch (randomCategory) {
                case 0 ->
                    // Número (0-9)
                        48 + random.nextInt(10);
                case 1 ->
                    // Letra maiúscula (A-Z)
                        65 + random.nextInt(26);
                default ->
                    // Letra minúscula (a-z)
                        97 + random.nextInt(26);
            };
            stringBuilder.append((char) randomAscii);
        }
        return stringBuilder.toString();
    }

}
