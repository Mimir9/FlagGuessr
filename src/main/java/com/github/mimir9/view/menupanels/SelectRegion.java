package com.github.mimir9.view.menupanels;

import com.github.mimir9.model.Data;
import com.github.mimir9.view.MainFrame;
import com.github.mimir9.view.components.RoundedButton;
import com.github.mimir9.view.gamemodes.PageMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectRegion extends JPanel implements ActionListener {

    String gameMode;

    RoundedButton returnButton = new RoundedButton("", new ImageIcon(Data.getResourcesPath() + "images/arrow-left.png"));

    RoundedButton europeButton = new RoundedButton("Europe", null);
    RoundedButton asiaButton = new RoundedButton("Asia", null);
    RoundedButton africaButton = new RoundedButton("Africa", null);
    RoundedButton oceaniaButton = new RoundedButton("Oceania", null);
    RoundedButton southAmericaButton = new RoundedButton("South America", null);
    RoundedButton northAmericaButton = new RoundedButton("North America", null);
    RoundedButton worldButton = new RoundedButton("World", null);

    RoundedButton[] regionButtons = {europeButton, asiaButton, africaButton, oceaniaButton,
            southAmericaButton, northAmericaButton, worldButton};

    public SelectRegion(String gameMode) {
        this.gameMode = gameMode;

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
        JLabel title = new JLabel("Select Region");
        title.setFont(new Font("Segoe UI Black", Font.ITALIC, 80));
        title.setForeground(Data.TEXT_COLOR);
        title.setAlignmentX(0.5F);

        // Creating buttons
        Box europeAsiaBox = Box.createHorizontalBox();
        europeAsiaBox.add(europeButton);
        europeAsiaBox.add(Box.createRigidArea(new Dimension(20,1)));
        europeAsiaBox.add(asiaButton);
        europeAsiaBox.setAlignmentX(0.5F);

        Box africaoceaniaBox = Box.createHorizontalBox();
        africaoceaniaBox.add(africaButton);
        africaoceaniaBox.add(Box.createRigidArea(new Dimension(20,1)));
        africaoceaniaBox.add(oceaniaButton);
        africaoceaniaBox.setAlignmentX(0.5F);

        Box americaBox = Box.createHorizontalBox();
        americaBox.add(southAmericaButton);
        americaBox.add(Box.createRigidArea(new Dimension(20,1)));
        americaBox.add(northAmericaButton);
        americaBox.setAlignmentX(0.5F);

        worldButton.setAlignmentX(0.5F);

        for (RoundedButton regionButton : regionButtons) {
            regionButton.addActionListener(this);
        }

        // Adding everything to the main panel
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(europeAsiaBox);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(africaoceaniaBox);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(americaBox);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(worldButton);

        // Adding everything to the class panel
        this.add(returnHorizontalBox, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(Box.createHorizontalStrut(115), BorderLayout.EAST);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==returnButton) {
            MainFrame.getCardLayout().show(MainFrame.getDisplayPanel(), "SelectGameMode");
        }
        else {
            for (RoundedButton regionButton : regionButtons) {
                if (e.getSource() == regionButton) {
                    regionSelected(regionButton.toString());
                }
            }
        }
    }

    public void regionSelected(String regionName) {
        // Starting game if region is selected
        if (gameMode.equals("PageMode")) {
            PageMode pageMode = new PageMode(regionName);
            MainFrame.getDisplayPanel().add(pageMode, "PageMode");
            MainFrame.getCardLayout().show(MainFrame.getDisplayPanel(), "PageMode");
        }
    }
}
