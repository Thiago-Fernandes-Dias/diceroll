package com.mycompany.app;

import java.util.Optional;

public class Helpers {
    // Convert a string to an Integer
    public static Optional<Integer> parseInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        } 
    }
}