package de.rohrjaspi.survivalv2main.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class Maths {

    public static Random rand = new Random();

    public static int getPercent(int howMuch, int fromWhat) {
        return (int) (fromWhat * (howMuch / 100.0f));
    }

    public static double getPercentDouble(int howMuch, double fromWhat) {
        return (fromWhat * (howMuch / 100.0f));
    }

    public static Long getPercentLong(int howMuch, long fromWhat) {
        return (long) (fromWhat * (howMuch / 100.0f));
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        if (Integer.parseInt(s) >= 1) {
            return true;
        }
        return false;
    }

    public static boolean isLong(String s) {
        try {
            Long.parseLong(s);
        } catch (NumberFormatException e) {
            return false;
        }
        if (Long.parseLong(s) >= 1) {
            return true;
        }
        return false;
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static int randInt(int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static String asString(int i) {
        return NumberFormat.getInstance().format(i);
    }

    public static String asString(long l) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(l);
    }

    public static String ToRoman(double num) {
        if (num > 3999) {
            throw new IllegalArgumentException("Input should be between 1 and 3999");
        }

        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();

        if (num==0) {
            return "0";
        }

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                result.append(symbols[i]);
            }
        }

        return result.toString();
    }

    public static boolean isBetween(int a, int b, int c) {
        return b > a ? c > a && c < b : c > b && c < a;
    }

    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double roundlong(double value) {


        String input = Double.toString(value);

        if (input == null || !input.contains(".")) {
            throw new IllegalArgumentException("Invalid input string");
        }

        String[] parts = input.split("\\.");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid input string format");
        }

        String integerPart = parts[0];
        String decimalPart = parts[1].length() > 2 ? parts[1].substring(0, 2) : parts[1];

        String truncatedString = integerPart + "." + decimalPart;
        return Double.parseDouble(truncatedString);
    }

    public static double randomDouble(double min, double max) {
        return min + (max - min) * rand.nextDouble();
    }

    public static double randomDouble() {
        return randInt(0, 1) == 1 ? Maths.randInt(1, 9) / 10 : -Maths.randInt(1, 9) / 10;
    }

}
