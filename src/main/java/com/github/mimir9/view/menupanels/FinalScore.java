package com.github.mimir9.view.menupanels;

import com.github.mimir9.model.Data;
import com.github.mimir9.view.MainFrame;
import com.github.mimir9.view.components.RoundedButton;
import com.github.mimir9.view.components.RoundedPanel;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FinalScore extends JPanel implements ActionListener {

    JLabel bestScoreLabel = new JLabel();
    JLabel yourScoreLabel = new JLabel();
    JLabel yourPercentageLabel = new JLabel();

    RoundedButton exitButton = new RoundedButton("Exit", null);

    public FinalScore(int score, int fullCountriesSize, String region) {
        bestScoresManager(score, fullCountriesSize, region);

        // Adjusting the class panel
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Data.BG_COLOR);

        // Creating title
        JLabel title = new JLabel("Summary");
        title.setFont(new Font("Segoe UI Black", Font.ITALIC, 80));
        title.setForeground(Data.TEXT_COLOR);
        title.setAlignmentX(0.5F);

        RoundedPanel informationPanel = new RoundedPanel();
        informationPanel.setAlignmentX(0.5F);
        informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.LINE_AXIS));

        RoundedPanel insideInformationPanel = new RoundedPanel();
        insideInformationPanel.setAlignmentY(0.5F);
        insideInformationPanel.setBackground(null);
        insideInformationPanel.setLayout(new BoxLayout(insideInformationPanel, BoxLayout.PAGE_AXIS));

        bestScoreLabel.setText("Best Score: "+score+"/"+fullCountriesSize);
        bestScoreLabel.setFont(new Font("Segoe UI Black", Font.ITALIC, 55));
        bestScoreLabel.setForeground(Data.TEXT_COLOR);
        bestScoreLabel.setAlignmentX(0F);
        
        yourScoreLabel.setText("Your Score: "+score+"/"+fullCountriesSize);
        yourScoreLabel.setFont(new Font("Segoe UI Black", Font.ITALIC, 55));
        yourScoreLabel.setForeground(Data.TEXT_COLOR);
        yourScoreLabel.setAlignmentX(0F);
        
        yourPercentageLabel.setText("Your Percentage: "+ (int)((double) score/fullCountriesSize*100) +"%");
        yourPercentageLabel.setFont(new Font("Segoe UI Black", Font.ITALIC, 55));
        yourPercentageLabel.setForeground(Data.TEXT_COLOR);
        yourPercentageLabel.setAlignmentX(0F);

        insideInformationPanel.add(Box.createVerticalStrut(10));
        insideInformationPanel.add(bestScoreLabel);
        insideInformationPanel.add(yourScoreLabel);
        insideInformationPanel.add(yourPercentageLabel);

        informationPanel.add(Box.createHorizontalStrut(30));
        informationPanel.add(insideInformationPanel);

        exitButton.addActionListener(this);
        exitButton.setAlignmentX(0.5F);
        exitButton.setMaximumSize(new Dimension(700, 110));

        this.add(Box.createVerticalStrut(10));
        this.add(title);
        this.add(Box.createVerticalStrut(20));
        this.add(informationPanel);
        this.add(Box.createVerticalStrut(20));
        this.add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==exitButton) {
            MainFrame.getCardLayout().show(MainFrame.getDisplayPanel(), "SelectGameMode");
        }
    }

    private void bestScoresManager(int score, int fullCountriesSize, String region) {
        File continentsDir = new File(Data.getResourcesPath()+"/countries/flags");
        String[] continents = continentsDir.list();
        HashMap<String, Integer> bestScoresHashMap = new HashMap<>();
        boolean newBest = false;

        try {
            Wini ini = new Wini(new File(Data.getResourcesPath() + "files/config.ini"));

            for (String language : Data.getLanguages()) {
                for (String continent : continents) {
                    bestScoresHashMap.put(language+"_"+continent, ini.get("BestScores", language+"_"+continent, Integer.class));
                    if (Data.getLanguage().equals(language) && region.equals(continent)) {
                        newBest = true;
                    }
                }
                bestScoresHashMap.put(language+"_world", ini.get("BestScores", language+"_world", Integer.class));
                if (Data.getLanguage().equals(language) && region.equals("World")) {
                    newBest = true;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
