package com.github.mimir9.view;

import com.github.mimir9.model.Data;
import com.github.mimir9.view.menupanels.MainMenu;
import com.github.mimir9.view.menupanels.SelectGameMode;
import com.github.mimir9.view.menupanels.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class MainFrame extends JFrame implements WindowListener {

    static JPanel displayPanel = new JPanel();
    static CardLayout cardLayout = new CardLayout();

    static MainMenu mainMenuPanel = new MainMenu();
    static Settings settingsPanel = new Settings();
    static SelectGameMode selectGameModePanel = new SelectGameMode();

    public static ArrayList<JPanel> panels = new ArrayList<>();

    public MainFrame() {
        // Setting frame parameters
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(new Dimension(1200, 800));
        this.setTitle("FlagGuessr");
        this.setIconImage(new ImageIcon(Data.getResourcesPath()+"/images/icon.png").getImage());
        this.addWindowListener(this);

        panels.add(mainMenuPanel);
        panels.add(settingsPanel);

        displayPanel.setLayout(cardLayout);

        // Adding panels to display panel
        displayPanel.add(mainMenuPanel, "MainMenu");
        displayPanel.add(settingsPanel, "Settings");
        displayPanel.add(selectGameModePanel, "SelectGameMode");

        this.add(displayPanel);
        this.setVisible(true);
    }

    public static JPanel getDisplayPanel() {
        return displayPanel;
    }

    public static CardLayout getCardLayout() {
        return cardLayout;
    }

    public static void updateTheme() {
        // Updating theme
        mainMenuPanel.updateColors();
        settingsPanel.updateColors();
        selectGameModePanel.updateColors();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // Saving language and theme when window is closing
        Data.writeIni();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}