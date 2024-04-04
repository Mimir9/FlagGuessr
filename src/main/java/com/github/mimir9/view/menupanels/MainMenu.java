package com.github.mimir9.view.menupanels;

import com.github.mimir9.model.Data;
import com.github.mimir9.view.MainFrame;
import com.github.mimir9.view.components.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener {

    JLabel title1 = new JLabel("FlagGuessr");
    static JLabel title2 = new JLabel("FlagGuessr");

    RoundedButton startButton = new RoundedButton("Start", null);
    RoundedButton settingsButton = new RoundedButton("Settings", null);

    public MainMenu() {
        // Adjusting panel
        this.setBackground(Data.BG_COLOR);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Making title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setBackground(null);

        title1.setFont(new Font("Segoe UI Black", Font.ITALIC, 112));
        title1.setForeground(Data.TEXT_COLOR);
        title1.setBounds(0, 0, 610, 160);

        title2.setFont(new Font("Segoe UI Black", Font.ITALIC, 112));
        title2.setForeground(Data.COLOR_1);
        title2.setBounds(5, 5, title1.getWidth(), title1.getHeight());

        titlePanel.add(title1);
        titlePanel.add(title2);
        titlePanel.setMaximumSize(new Dimension(title1.getWidth()+5, title1.getHeight()));
        titlePanel.setMinimumSize(new Dimension(title1.getWidth()+5, title1.getHeight()));
        titlePanel.setAlignmentX(0.5F);

        // Adjusting buttons
        startButton.addActionListener(this);
        startButton.setAlignmentX(0.5F);

        settingsButton.addActionListener(this);
        settingsButton.setAlignmentX(0.5F);

        // Adding everything to the panel
        this.add(Box.createVerticalStrut(20));
        this.add(titlePanel);
        this.add(Box.createVerticalStrut(75));
        this.add(startButton);
        this.add(Box.createVerticalStrut(70));
        this.add(settingsButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==startButton) {
            MainFrame.getCardLayout().show(MainFrame.getDisplayPanel(), "SelectGameMode");
        }
        else if (e.getSource()==settingsButton) {
            MainFrame.getCardLayout().show(MainFrame.getDisplayPanel(), "Settings");
        }
    }

    public void updateColors() {
        title2.setForeground(Data.COLOR_1);
        this.setBackground(Data.BG_COLOR);
        startButton.setBackground(Data.COLOR_1);
        settingsButton.setBackground(Data.COLOR_1);
    }
}
