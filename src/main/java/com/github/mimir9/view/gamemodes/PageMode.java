package com.github.mimir9.view.gamemodes;

import com.github.mimir9.model.Country;
import com.github.mimir9.model.Data;
import com.github.mimir9.view.MainFrame;
import com.github.mimir9.view.components.CustomTextField;
import com.github.mimir9.view.components.RoundedButton;
import com.github.mimir9.view.menupanels.FinalScore;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class PageMode extends JPanel implements ActionListener, DocumentListener {

    String region;
    ArrayList<Country> countries = new ArrayList<>();
    int currentCountryIndex = 0;
    int fullCountriesSize;

    RoundedButton exitButton = new RoundedButton("", new ImageIcon(Data.getResourcesPath()+"images/exit-icon.png"));
    RoundedButton finishButton = new RoundedButton("Finish", null);

    JLabel scoreLabel = new JLabel("0/197");
    JLabel countryFlag = new JLabel();
    CustomTextField textField = new CustomTextField();
    RoundedButton previousButton = new RoundedButton("", new ImageIcon(Data.getResourcesPath()+"images/arrow-left.png"));
    RoundedButton nextButton = new RoundedButton("", new ImageIcon(Data.getResourcesPath()+"images/arrow-right.png"));
    JPanel flagPanel = new JPanel();

    public PageMode(String region) {
        // Preparing lis of countries
        this.region = region;
        prepareCountries();

        // Adjusting panel
        this.setLayout(new BorderLayout());
        this.setBackground(Data.BG_COLOR);

        // Creating top Box
        Box topVerticalBox = Box.createVerticalBox();
        Box topHorizontalBox = Box.createHorizontalBox();

        scoreLabel.setFont(new Font("Segoe UI Black", Font.ITALIC, 80));
        scoreLabel.setForeground(Data.TEXT_COLOR);

        exitButton.addActionListener(this);
        finishButton.addActionListener(this);
        finishButton.setPreferredSize(new Dimension(300, 100));

        topHorizontalBox.add(Box.createHorizontalStrut(15));
        topHorizontalBox.add(exitButton);
        topHorizontalBox.add(Box.createHorizontalStrut(200));
        topHorizontalBox.add(Box.createHorizontalGlue());
        topHorizontalBox.add(scoreLabel);
        topHorizontalBox.add(Box.createHorizontalGlue());
        topHorizontalBox.add(finishButton);
        topHorizontalBox.add(Box.createHorizontalStrut(15));

        topVerticalBox.add(Box.createVerticalStrut(15));
        topVerticalBox.add(topHorizontalBox);

        // Creating the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(null);

        // Creating box with flag and buttons to change country
        Box countryFlagBox = Box.createHorizontalBox();

        flagPanel.setBackground(null);
        flagPanel.setMaximumSize(new Dimension(870, 360));
        flagPanel.setLayout(new BoxLayout(flagPanel, BoxLayout.PAGE_AXIS));

        updateAfterGuessed();
        countryFlag.setBorder(new LineBorder(Color.black, 1));
        countryFlag.setAlignmentX(0.5F);

        flagPanel.add(countryFlag, BorderLayout.CENTER);

        previousButton.addActionListener(this);
        nextButton.addActionListener(this);

        countryFlagBox.add(previousButton);
        countryFlagBox.add(Box.createRigidArea(new Dimension(30, 1)));
        countryFlagBox.add(flagPanel);
        countryFlagBox.add(Box.createRigidArea(new Dimension(30, 1)));
        countryFlagBox.add(nextButton);

        // Creating text field
        textField.setAlignmentX(0.5F);
        textField.getDocument().addDocumentListener(this);

        // Adding everything to the main panel
        mainPanel.add(Box.createVerticalStrut(35));
        mainPanel.add(countryFlagBox);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(textField);

        // Adding everything to the class panel
        this.add(topVerticalBox, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==exitButton) {
            MainFrame.getCardLayout().show(MainFrame.getDisplayPanel(), "SelectRegion");
        }
        else if (e.getSource()==previousButton) {
            nextFlag('-');
        }
        else if (e.getSource()==nextButton) {
            nextFlag('+');
        }
        else if (e.getSource()==finishButton) {
            createFinalScore();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        checkIfGuessed();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        checkIfGuessed();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    private void prepareCountries() {
        // Making countries list with countries from region you chose
        ArrayList<Country> allCountriesList = Data.getCountries();

        if (region.equals("World")) {
            countries.addAll(allCountriesList);
        }
        else {
            for (Country country : allCountriesList) {
                if (region.equalsIgnoreCase(country.getContinent().replaceAll("-", " "))) {
                    countries.add(country);
                }
            }
        }
        Collections.shuffle(countries);
        fullCountriesSize = countries.size();
    }

    private void checkIfGuessed() {
        // Checking if you guessed country
        Country country = countries.get(currentCountryIndex);
        String language = Data.getLanguage();

        if (language.equals("EN") && textField.getText().trim().equalsIgnoreCase(country.getName())) {
            guessed(country);
        }
        else if (country.getLanguages().get(language)!=null){
            for (String name : country.getLanguages().get(language)) {
                if (textField.getText().trim().equalsIgnoreCase(name)) {
                    guessed(country);
                }
            }
        }
    }

    private void guessed(Country country) {
        // Removing guessed country
        countries.remove(country);

        // If you guessed all countries it opens panel with dfinal score
        if (countries.isEmpty()) {
            createFinalScore();
        }
        else {
            // Updating components after country is guessed
            if (currentCountryIndex==countries.size()){
                nextFlag('-');
            }
            else {
                updateAfterGuessed();
            }
        }
    }

    private void updateAfterGuessed() {
        // Updating flag if not all countries are guessed
        if (!countries.isEmpty()) {
            countryFlag.setIcon(new ImageIcon(Data.getResourcesPath()+"/countries/flags/"+countries.get(currentCountryIndex).getContinent()+"/"+countries.get(currentCountryIndex).getName()+".png"));
        }
        else {
            countryFlag.setIcon(null);
        }

        // Removing border around Nepal flag
        if (countries.get(currentCountryIndex).getName().equals("Nepal")) {
            countryFlag.setBorder(null);
        }
        else {
            countryFlag.setBorder(new LineBorder(Color.black, 1));
        }

        // Aligning flag if width is odd
        if (countryFlag.getIcon().getIconWidth()%2!=0){
            flagPanel.setBorder(new EmptyBorder(0,0,0,1));
        }
        else {
            flagPanel.setBorder(null);
        }

        // Clearing text field
        SwingUtilities.invokeLater(
                new Runnable(){
                    @Override
                    public void run() {
                        textField.setText("");
                    }
                }
        );

        // Updating label that shows score
        scoreLabel.setText(fullCountriesSize-countries.size()+"/"+fullCountriesSize);
    }

    private void nextFlag(char symbol) {
        // Changing flag on button click
        if (symbol=='-' && currentCountryIndex >0) {
            currentCountryIndex -=1;
            updateAfterGuessed();
        } else if (symbol=='+' && currentCountryIndex < countries.size()-1) {
            currentCountryIndex +=1;
            updateAfterGuessed();
        }
    }

    private void createFinalScore() {
        // Creating and opening final score label
        FinalScore finalScore = new FinalScore(fullCountriesSize-countries.size(), fullCountriesSize, region);
        MainFrame.getDisplayPanel().add(finalScore, "FinalScore");
        MainFrame.getCardLayout().show(MainFrame.getDisplayPanel(), "FinalScore");
    }
}
