package com.github.mimir9.view.menupanels;

import com.github.mimir9.model.Data;
import com.github.mimir9.view.MainFrame;
import com.github.mimir9.view.components.CustomRadioButton;
import com.github.mimir9.view.components.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Settings extends JPanel implements ActionListener {

    RoundedButton returnButton = new RoundedButton("", new ImageIcon(Data.getResourcesPath( )+"images/arrow-left.png"));
    CustomRadioButton lightThemeRadioButton = new CustomRadioButton("Light");
    CustomRadioButton darkThemeRadioButton = new CustomRadioButton("Dark");

    public Settings() {
        // Adjusting the class panel
        this.setLayout(new BorderLayout());
        this.setBackground(Data.BG_COLOR);

        // Creating the side panel with return button
        Box returnVerticalBox = Box.createVerticalBox();
        returnVerticalBox.add(Box.createVerticalStrut(15));
        returnVerticalBox.add(returnButton);
        returnVerticalBox.setAlignmentY(0F);

        Box returnHorizontalBox = Box.createHorizontalBox();
        returnHorizontalBox.setOpaque(true);
        returnHorizontalBox.add(Box.createHorizontalStrut(15));
        returnHorizontalBox.add(returnVerticalBox);

        returnButton.addActionListener(this);

        // Creating main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(null);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setOpaque(true);

        // Creating title
        JLabel title = new JLabel("Settings");
        title.setFont(new Font("Segoe UI Black", Font.ITALIC, 80));
        title.setForeground(Data.TEXT_COLOR);
        title.setAlignmentX(0.5F);

        // Creating options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(null);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.PAGE_AXIS));
        optionsPanel.setAlignmentX(0.5F);

        // Creating themes Label
        JLabel themeLabel = new JLabel("Choose Theme:");
        themeLabel.setFont(new Font("Segoe UI Black", Font.ITALIC, 52));
        themeLabel.setForeground(Data.TEXT_COLOR);

        // Creating themes buttons
        ButtonGroup themeButtons = new ButtonGroup();

        lightThemeRadioButton.addActionListener(this);
        lightThemeRadioButton.setAlignmentX(0F);
        themeButtons.add(lightThemeRadioButton);

        darkThemeRadioButton.addActionListener(this);
        darkThemeRadioButton.setAlignmentX(0F);
        themeButtons.add(darkThemeRadioButton);

        themeButtons.setSelected(lightThemeRadioButton.getModel(), true);

        // Creating languages Label
        JLabel languageLabel = new JLabel("Choose language:");
        languageLabel.setFont(new Font("Segoe UI Black", Font.ITALIC, 52));
        languageLabel.setForeground(Data.TEXT_COLOR);

        // Adding everything to the options panel
        optionsPanel.add(themeLabel);
        optionsPanel.add(lightThemeRadioButton);
        optionsPanel.add(darkThemeRadioButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        optionsPanel.add(languageLabel);

        // Adding everything to the main panel
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(optionsPanel);

        // Adding everything to the class panel
        this.add(returnHorizontalBox, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(Box.createHorizontalStrut(115), BorderLayout.EAST);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==returnButton) {
            MainFrame.getCardLayout().show(MainFrame.getDisplayPanel(), "MainMenu" );
        }
    }
}
