package com.example.restorant.utils;

public class ValidationUtils {
    
    /**
     * Validates if a string is not null and not empty
     */
    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }
    
    /**
     * Validates username format
     */
    public static boolean isValidUsername(String username) {
        if (!isNotEmpty(username)) {
            return false;
        }
        // Username should be at least 3 characters
        return username.trim().length() >= 3;
    }
    
    /**
     * Validates password format
     */
    public static boolean isValidPassword(String password) {
        if (!isNotEmpty(password)) {
            return false;
        }
        // Password should be at least 6 characters
        return password.length() >= 6;
    }
    
    /**
     * Checks if two passwords match
     */
    public static boolean passwordsMatch(String password1, String password2) {
        return isNotEmpty(password1) && isNotEmpty(password2) && password1.equals(password2);
    }
}
