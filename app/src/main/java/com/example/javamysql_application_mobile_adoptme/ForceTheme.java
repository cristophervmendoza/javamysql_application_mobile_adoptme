package com.example.javamysql_application_mobile_adoptme;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatDelegate;

public class ForceTheme {

    private static final String PREFS_NAME = "ThemePrefs";
    private static final String KEY_THEME = "theme_mode";

    public static final int MODE_LIGHT = 0;
    public static final int MODE_DARK = 1;
    public static final int MODE_SYSTEM = 2;


    public static void setLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }


    public static void setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }


    public static void setSystemMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }


    public static void applyTheme(int mode) {
        switch (mode) {
            case MODE_LIGHT:
                setLightMode();
                break;
            case MODE_DARK:
                setDarkMode();
                break;
            case MODE_SYSTEM:
                setSystemMode();
                break;
            default:
                setSystemMode();
                break;
        }
    }


    public static void saveThemePreference(Context context, int mode) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(KEY_THEME, mode).apply();
        applyTheme(mode);
    }


    public static int getSavedThemePreference(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(KEY_THEME, MODE_SYSTEM); // Por defecto usa el sistema
    }


    public static void loadSavedTheme(Context context) {
        int savedTheme = getSavedThemePreference(context);
        applyTheme(savedTheme);
    }


    public static boolean isDarkModeActive() {
        int currentMode = AppCompatDelegate.getDefaultNightMode();
        return currentMode == AppCompatDelegate.MODE_NIGHT_YES;
    }


    public static void toggleTheme(Context context) {
        int currentTheme = getSavedThemePreference(context);
        if (currentTheme == MODE_DARK) {
            saveThemePreference(context, MODE_LIGHT);
        } else {
            saveThemePreference(context, MODE_DARK);
        }
    }
}
