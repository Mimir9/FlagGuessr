package com.github.mimir9.view.components;

import com.github.mimir9.model.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {

    private String text;
    private boolean over;
    private int radius = 0;

    public RoundedButton(String text, ImageIcon icon) {
        this.text = text;

        // Setting value of button
        if (!text.isBlank()) {
            this.setText(text);
            this.setMaximumSize(new Dimension(530, 110));
            this.setPreferredSize(new Dimension(530, 110));
        } else if (icon!=null) {
            this.setIcon(icon);
            this.setMaximumSize(new Dimension(100, 100));
            this.setPreferredSize(new Dimension(100, 100));
        }

        // Setting Parameters
        this.setBackground(Data.COLOR_1);
        this.setContentAreaFilled(false);
        this.setFocusable(false);
        this.setRadius(80);
        this.setBorder(null);
        this.setFont(new Font("Segoe UI Black", Font.ITALIC, 62));
        this.setVerticalTextPosition(JButton.TOP);
        this.setForeground(Data.TEXT_COLOR);

        // Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(Data.COLOR_2);
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(Data.COLOR_1);
                over = false;

            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(Data.COLOR_3);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    setBackground(Data.COLOR_2);
                } else {
                    setBackground(Data.COLOR_1);
                }
            }
        });
    }



    @Override
    protected void paintComponent(Graphics grphcs) {
        // Drawing button shape
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return text;
    }
}
