package com.github.mimir9.view.menupanels;

import com.github.mimir9.model.Data;
import com.github.mimir9.view.MainFrame;
import com.github.mimir9.view.components.RoundedButton;
import com.github.mimir9.view.components.RoundedPanel;
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

        // Creating Panel
        RoundedPanel informationPanel = new RoundedPanel();
        informationPanel.setAlignmentX(0.5F);
        informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.LINE_AXIS));

        RoundedPanel insideInformationPanel = new RoundedPanel();
        insideInformationPanel.setAlignmentY(0.5F);
        insideInformationPanel.setBackground(null);
        insideInformationPanel.setLayout(new BoxLayout(insideInformationPanel, BoxLayout.PAGE_AXIS));

        // Creating panels information labels
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

        // Adding everything
        insideInformationPanel.add(Box.createVerticalStrut(10));
        insideInformationPanel.add(bestScoreLabel);
        insideInformationPanel.add(yourScoreLabel);
        insideInformationPanel.add(yourPercentageLabel);

        informationPanel.add(Box.createHorizontalStrut(30));
        informationPanel.add(insideInformationPanel);

        // Adjusting exit button
        exitButton.addActionListener(this);
        exitButton.setAlignmentX(0.5F);
        exitButton.setMaximumSize(new Dimension(700, 110));

        // Adding everything to the main panel
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
        String[] continents = new String[7];
        HashMap<String, Integer> bestScoresHashMap = new HashMap<>();
        boolean newBest = false;

        for (int i=0; i<continentsDir.list().length; i++) {
            continents[i] = continentsDir.list()[i];
        }
        continents[6] = "world";

        try {
            Wini ini = new Wini(new File(Data.getResourcesPath() + "files/config.ini"));

            for (String language : Data.getLanguages()) {
                Integer bestScore;

                for (String continent : continents) {
                    // Reading best score from the file
                    bestScore = ini.get("BestScores", language+"_"+continent.replace("-", "_"), Integer.class);

                    // Checking if new best score is set
                    if (Data.getLanguage().equals(language) && region.equalsIgnoreCase(continent.replace("-", " "))) {
                        if (score>bestScore) {
                            newBest = true;
                            bestScore = score;
                        }
                        // Setting value of label that shows best score
                        bestScoreLabel.setText("Best Score: "+bestScore+"/"+fullCountriesSize);
                    }
                    // Adding best scores to hash map
                    bestScoresHashMap.put(language+"_"+continent.replace("-", "_"), bestScore);
                }
            }

            // Updating best scores in ini file if new best score is set
            if (newBest) {
                for (String language : Data.getLanguages()) {
                    for (String continent : continents) {
                        ini.put("BestScores", language+"_"+continent.replace("-", "_"), bestScoresHashMap.get(language+"_"+continent.replace("-", "_")));
                    }
                }
                ini.store();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
