package com.github.mimir9.view.components;

import com.github.mimir9.model.Data;

import javax.swing.*;
import java.awt.*;

public class CustomRadioButton extends JRadioButton {

    private final int SIZE = 54;

    public CustomRadioButton(String text) {
        // Setting parameters
        this.setBackground(null);
        this.setFont(new Font("Segoe UI Black", Font.ITALIC, 40));
        this.setForeground(Color.white);
        this.setFocusable(false);
        this.setIconTextGap(50);
        this.setText(text);
    }

    @Override
    public void paint(Graphics grphcs) {
        // Drawing radio buttons
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int ly = (this.getHeight()-SIZE)/2;

        g2.setColor(Data.COLOR_1);
        g2.fillOval(0, ly+3, SIZE, SIZE);

        g2.setColor(Data.COLOR_4);
        g2.fillOval(7, ly+3+7, SIZE-14, SIZE-14);

        g2.setColor(Data.TEXT_COLOR);

        if (this.isSelected()) {
            g2.fillOval(16, ly+3+16, SIZE-32, SIZE-32);
        }
    }
}
