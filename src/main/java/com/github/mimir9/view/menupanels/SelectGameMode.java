package com.github.mimir9.view.menupanels;

import com.github.mimir9.model.Data;
import com.github.mimir9.view.MainFrame;
import com.github.mimir9.view.components.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectGameMode extends JPanel implements ActionListener {

    RoundedButton returnButton = new RoundedButton("", new ImageIcon(Data.getResourcesPath() + "images/arrow-left.png"));
    RoundedButton pageModeButton = new RoundedButton("PageMode", null);

    public SelectGameMode() {
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
        JLabel title = new JLabel("Select Game Mode");
        title.setFont(new Font("Segoe UI Black", Font.ITALIC, 80));
        title.setForeground(Data.TEXT_COLOR);
        title.setAlignmentX(0.5F);

        // Creating buttons
        pageModeButton.setAlignmentX(0.5F);
        pageModeButton.addActionListener(this);

        // Adding everything to the main panel
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(70));
        mainPanel.add(pageModeButton);

        // Adding everything to the class panel
        this.add(returnHorizontalBox, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(Box.createHorizontalStrut(115), BorderLayout.EAST);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==returnButton) {
            MainFrame.getCardLayout().show(MainFrame.getDisplayPanel(), "MainMenu");
        }
        else if (e.getSource()==pageModeButton) {
            SelectRegion selectRegionPanel = new SelectRegion("PageMode");
            MainFrame.getDisplayPanel().add(selectRegionPanel, "SelectRegion");
            MainFrame.getCardLayout().show(MainFrame.getDisplayPanel(), "SelectRegion");
        }
    }

    public void updateColors() {
        this.setBackground(Data.BG_COLOR);
        returnButton.setBackground(Data.COLOR_1);
        pageModeButton.setBackground(Data.COLOR_1);
    }
}
