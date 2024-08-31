package fr.jypast.parisjanitorapi.domain.functionnal.service;

import java.util.Random;


public class RandomStringGenerator {

    public static String generateString(int targetStringLength, int leftLimit, int rightLimit) {

        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String generateAlphabeticString(int targetStringLength) {
        int leftLimit = 65; // letter 'A'
        int rightLimit = 90; // letter 'Z'
        return generateString(targetStringLength, leftLimit, rightLimit);
    }

    public static String generateAlphanumericString(int targetStringLength) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 90; // letter 'Z'

        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || (i >= 65 && i <= 90)))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
