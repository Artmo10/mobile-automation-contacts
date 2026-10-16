package utils;

import java.util.Random;

public class TestData {

    private static final Random RANDOM = new Random();

    public static String randomLetters(int length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + RANDOM.nextInt(26)));
        }
        return sb.toString();
    }
}