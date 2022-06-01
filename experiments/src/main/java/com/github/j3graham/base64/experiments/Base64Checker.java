package com.github.j3graham.base64.experiments;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Base64Checker {
    static final byte[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".getBytes(StandardCharsets.ISO_8859_1);
    static final int[] values = genValueMapping();

    Base64Checker() {

    }

    private static int[] genValueMapping() {
        int[] values = new int[256];
        Arrays.fill(values, -1);
        for (int i = 0; i < alphabet.length; i++) {
            values[alphabet[i] & 0xff] = i;
        }
        return values;
    }

    public boolean isAcceptable(String s) {
        int goodChars = 0;
        int lastGoodCharVal = -1;
        for (int i = 0; i < s.length(); i++) {
            int val = values[0xff & s.charAt(i)];
            if (val != -1) {
                lastGoodCharVal = val;
                goodChars++;
            }
        }

        return switch (goodChars % 4) {
            case 0 -> true;
            case 2 -> (lastGoodCharVal & 0b1111) == 0;
            case 3 -> (lastGoodCharVal & 0b11) == 0;
            default -> false;
        };
    }

}
