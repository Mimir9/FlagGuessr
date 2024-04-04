package com.github.mimir9.view.components;

import com.github.mimir9.model.Data;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public RoundedPanel() {
        // Setting parameters
        setOpaque(false);
        setBackground(Data.COLOR_1);
        setRadius(120);
        setMaximumSize(new Dimension(700,350));
    }

    private int radius = 0;

    @Override
    protected void paintComponent(Graphics grphcs) {
        // Drawing panel
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }
}
