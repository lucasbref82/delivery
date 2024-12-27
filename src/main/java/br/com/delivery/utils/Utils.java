package br.com.delivery.utils;

import org.slf4j.helpers.MessageFormatter;

import java.security.SecureRandom;

public class Utils {

    private Utils() {

    }

    public static String format(String format, Object... arguments) {
        return MessageFormatter.basicArrayFormat(format, arguments);
    }

    public static String gerarStringRandomica(int tamanho) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(tamanho);
        for (int i = 0; i < tamanho; i++) {
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
