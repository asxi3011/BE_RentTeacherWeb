package com.example.demo.utils;

import java.text.Normalizer;

public class StringUtils {
    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    public static String toSlug(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }


        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String withoutDiacritics = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");


        withoutDiacritics = withoutDiacritics.toLowerCase();


        withoutDiacritics = withoutDiacritics.replaceAll("[^a-z0-9\\s]", "");


        withoutDiacritics = withoutDiacritics.replaceAll("\\s+", "-");


        return withoutDiacritics.replaceAll("^-|-$", "");
    }
}
