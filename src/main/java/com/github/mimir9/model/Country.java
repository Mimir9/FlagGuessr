package com.github.mimir9.model;

import java.io.Serializable;
import java.util.HashMap;

public class Country implements Serializable {

    private String name;
    private String continent;
    private HashMap<String, String[]> languages;

    public Country(String name, String continent, HashMap<String, String[]> namesList) {
        this.name = name;
        this.continent = continent;
        this.languages = namesList;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public HashMap<String, String[]> getLanguages() {
        return languages;
    }

    @Override
    public String toString () {
        return name;
    }
}
