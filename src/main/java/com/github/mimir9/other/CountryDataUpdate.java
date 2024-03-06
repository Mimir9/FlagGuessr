package com.github.mimir9.other;

import com.github.mimir9.model.Country;
import com.github.mimir9.model.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CountryDataUpdate {

    public static void main(String[] args) throws IOException {

        ArrayList<Country> countriesList = new ArrayList<>();

        File languagesDir = new File(Data.getResourcesPath()+"/countries/languages");
        String[] languagesFiles = languagesDir.list();

        File continentsDir = new File(Data.getResourcesPath()+"/countries/flags");
        String[] continents = continentsDir.list();

        // Making loop through all continents
        for (String continent : continents) {

            File countriesDir = new File(Data.getResourcesPath()+"/countries/flags/"+continent);
            String[] countries = countriesDir.list();

            // Making loop through all countries
            for (String country : countries) {

                country = country.replace(".png", "");
                HashMap<String, String[]> languages = new HashMap<>();

                // Making loop through all languages
                for (String file : languagesFiles) {

                    Scanner languagesFileScanner = new Scanner(new File(Data.getResourcesPath()+"/countries/languages/" + file));

                    while (languagesFileScanner.hasNextLine()) {

                        String fileLine = languagesFileScanner.nextLine();

                        if (fileLine.split("=")[0].equals(country)) {

                            languages.put(file.replace(".txt", ""), fileLine.split("=")[1].split(","));
                            break;
                        }
                    }
                }

                // Adding new Country to the list
                Country newCountry = new Country(country, continent, languages);
                countriesList.add(newCountry);

                System.out.println(countriesList.size()+"/197");
            }
        }

        // Saving ArrayList to the file
        FileOutputStream fileOut = new FileOutputStream(Data.getResourcesPath()+"files/countries-save.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        out.writeObject(countriesList);
        out.close();
        fileOut.close();
    }
}
