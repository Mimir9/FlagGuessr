package com.github.mimir9.view.components;

import com.github.mimir9.model.Data;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField{

    public CustomTextField() {
        // Setting parameters
        this.setBorder(null);
        this.setBackground(null);
        this.setMaximumSize(new Dimension(790, 100));
        this.setPreferredSize(new Dimension(790, 100));
        this.setFont(new Font("Segoe UI Black", Font.ITALIC, 40));
        this.setForeground(Data.COLOR_1);
        this.setOpaque(false);
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setCaretColor(Data.COLOR_1);
    }

    @Override
    public void paintComponent(Graphics grphcs) {
        // Drawing text field
        final Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Data.COLOR_1);
        g2.fillRoundRect(0, 0, 790, 100, 100, 100);

        g2.setColor(Data.TEXT_COLOR);
        g2.fillRoundRect(8, 8, 774, 84, 85, 85);

        g2.dispose();

        super.paintComponent(grphcs);
    }
}
