package com.solvians.showcase;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class ISINGenerator {

    private static final int ISIN_LENGTH = 12;
    private static final int RANDOM_PART_LENGTH = 9;

    public String generateISIN() {
        StringBuilder isinBuilder = new StringBuilder(ISIN_LENGTH);

        IntStream.range(0, 2).forEach(i -> isinBuilder.append(generateRandomUppercaseLetter()));

        IntStream.range(0, RANDOM_PART_LENGTH).forEach(i ->
                isinBuilder.append(ThreadLocalRandom.current().nextBoolean()
                        ? (char) (ThreadLocalRandom.current().nextInt(10) + '0')
                        : generateRandomUppercaseLetter())
        );

        char checkDigit = calculateCheckDigit(isinBuilder.toString());
        return isinBuilder.append(checkDigit).toString();
    }

    private char generateRandomUppercaseLetter() {
        return (char) (ThreadLocalRandom.current().nextInt(26) + 'A');
    }

    private char calculateCheckDigit(String isin) {
        int sum = IntStream.range(0, isin.length())
                .map(i -> {
                    char c = isin.charAt(isin.length() - 1 - i);
                    int value = Character.isDigit(c) ? Character.getNumericValue(c) : convertLetterToNumber(c);
                    return i % 2 == 0 ? value : (value * 2 > 9 ? value * 2 - 9 : value * 2);
                })
                .sum();

        return (char) ((10 - (sum % 10)) % 10 + '0');
    }

    private int convertLetterToNumber(char letter) {
        return letter - 'A' + 10;
    }
}