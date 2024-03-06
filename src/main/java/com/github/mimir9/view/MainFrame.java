package com.github.mimir9.view;

import com.github.mimir9.model.Data;
import com.github.mimir9.view.menupanels.MainMenu;
import com.github.mimir9.view.menupanels.Settings;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    static JPanel displayPanel = new JPanel();
    static CardLayout cardLayout = new CardLayout();

    MainMenu mainMenuPanel = new MainMenu();
    Settings settingsPanel = new Settings();

    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(new Dimension(750, 500));
        this.setTitle("FlagGuessr");
        this.setIconImage(new ImageIcon(Data.getResourcesPath()+"/images/icon.png").getImage());

        displayPanel.setLayout(cardLayout);

        displayPanel.add(mainMenuPanel, "MainMenu");
        displayPanel.add(settingsPanel, "Settings");

        this.add(displayPanel);
        this.setVisible(true);
    }

    public static JPanel getDisplayPanel() {
        return displayPanel;
    }

    public static CardLayout getCardLayout() {
        return cardLayout;
    }
}
