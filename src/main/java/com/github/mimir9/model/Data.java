package com.github.mimir9.model;

import org.ini4j.Wini;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Data {

    private static ArrayList<Country> countries;
    private static String[] languages;
    private static String resourcesPath;

    public static Color TEXT_COLOR = new Color(255, 255, 255);;

    public static Color BG_COLOR;
    public static Color COLOR_1;
    public static Color COLOR_2;
    public static Color COLOR_3;
    public static Color COLOR_4;

    private static String language = "EN";
    private static String theme = "Dark";

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        Data.language = language;
    }

    public static String getTheme() {
        return theme;
    }

    public static void setTheme(String themeName) {
        theme = themeName;

        if (themeName.equals("Light")) {
            BG_COLOR = new Color(0, 178, 255);
            COLOR_1 = new Color(0, 136, 255);
            COLOR_2 = new Color(17, 144, 255);
            COLOR_3 = new Color(0, 129, 242);
            COLOR_4 = new Color(0, 122, 229);
        }
        else if (themeName.equals("Dark")) {
            BG_COLOR = new Color(46, 52, 58);
            COLOR_1 = new Color(38, 43, 48);
            COLOR_2 = new Color(39, 44, 50);
            COLOR_3 = new Color(36, 41, 45);
            COLOR_4 = new Color(32, 36, 40);
        }
    }

    public static ArrayList<Country> getCountries() {
        return countries;
    }

    public static String[] getLanguages() {
        return languages;
    }

    public static String getResourcesPath() {
        return resourcesPath;
    }

    public static void writeIni() {
        // Saving informations about language and theme
        try {
            Wini ini = new Wini(new File(getResourcesPath()+"files/config.ini"));

            ini.put("Data", "language", language);
            ini.put("Data", "theme", theme);

            ini.store();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readIni() {
        // Reading informations about language and theme
        try{
            Wini ini = new Wini(new File(getResourcesPath()+"files/config.ini"));

            setLanguage(ini.get("Data", "language", String.class));
            setTheme(ini.get("Data", "theme", String.class));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deserializeCountries() {

        // Reading countries information from file
        try {
            File file = new File(getResourcesPath()+"files/countries-save.ser");
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            countries = (ArrayList<Country>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void makeLanguagesList() {
        // Making list with all languages
        File languagesDir = new File(getResourcesPath() + "countries/languages");
        languages = languagesDir.list();

        for (int i=0; i<languages.length; i++) {
            languages[i] = languages[i].replace(".txt", "");
        }
    }

    private static void setResourcesPath() {
        // Checking if program was run in jar or in ide and setting the resources path
        String path;
        if (Data.class.getResource("Data.class").toString().startsWith("jar:")) {
            try {
                File file = new File(Data.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
                path = file.getParent()+"\\resources\\";
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            path = "src/main/resources/";
        }
        resourcesPath = path;
    }

    public static void afterStartFunctions() {
        // Functions used after application start
        setResourcesPath();
        readIni();
        deserializeCountries();
        makeLanguagesList();
    }

}