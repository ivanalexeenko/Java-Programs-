package com.textart;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

public class TextPanel extends JPanel {

    private static final int NUM_SHADOW = 4;
    private static final int NUM_DIRECTION = 4;
    private static final int NUM_COLOR = 3;

    private JTextField text;
    private JButton shadowButton;
    private JButton directionButton;

    private JButton colorButton;
    private int shadow = 0;
    private int direction = 0;
    private int color = 0;

    public TextPanel() {
        super(new BorderLayout());


        text = new JTextField();;
        shadowButton = new JButton("Change shadow");
        directionButton = new JButton("Change direction");
        colorButton = new JButton("Change color");
        JPanel panel = new JPanel(new GridLayout(2, 3));

        panel.add(new JLabel());
        panel.add(text);
        panel.add(new JLabel());

        panel.add(shadowButton);
        panel.add(directionButton);
        panel.add(colorButton);

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(WIDTH,100));
        add(northPanel,BorderLayout.NORTH);
        MiniWordArtPanel wordArtPanel = new MiniWordArtPanel();
        add(wordArtPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);


        initListeners();
    }

    private void initListeners() {
        text.addCaretListener(e -> repaint());
        colorButton.addActionListener(e -> {
            color = (color + 1) % NUM_COLOR;
            repaint();
        });
        directionButton.addActionListener(e -> {
            direction = (direction + 1) % NUM_DIRECTION;
            repaint();
        });
        shadowButton.addActionListener(e -> {
            shadow = (shadow + 1) % NUM_SHADOW;
            repaint();
        });
    }

    private class MiniWordArtPanel extends JPanel {
        MiniWordArtPanel() {
            super();
        }
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D graphics = (Graphics2D) g;
            String str = text.getText().trim() + " ";
            TextLayout txt = new TextLayout(str,
                    new Font("Bernard MT", Font.BOLD, (2) * getHeight() / (str.length() + 1)),
                    graphics.getFontRenderContext());
            AffineTransform transform = new AffineTransform();
            Shape shape;
            for (int i = 0; i < 50 / str.length(); ++i) {
                translateShadow(transform, i);
                shape = txt.getOutline(transform);
                graphics.draw(shape);
                graphics.fill(shape);
            }
            for (int i = 0; i < 75 / str.length(); ++i) {
                translateColor(graphics, i, str);
                translateDirection(transform, i);

                shape = txt.getOutline(transform);
                graphics.draw(shape);
                graphics.fill(shape);
            }
        }

        private void translateShadow(AffineTransform transform, int i) {
            if (shadow == 0) {
                transform.setToTranslation(200 - i, getWidth() / 3);
            } else if (shadow == 1) {
                transform.setToTranslation(200, getWidth() / 3 + i);
            } else if (shadow == 2) {
                transform.setToTranslation(200 + i, getWidth() / 3);
            } else if (shadow == 3) {
                transform.setToTranslation(200, getWidth() / 3 - i);
            }
        }
        private void translateColor(Graphics graphics, int i, String str) {
            if (color == 0) {
                graphics.setColor(new Color(0, 50 + i * 3 / 2 * str.length(), 0));
            } else if (color == 1) {
                graphics.setColor(new Color(0, 0, 50 + i * 3 / 2 * str.length()));
            } else if (color == 2) {
                graphics.setColor(new Color(50 + i * 3 / 2 * str.length(), 0, 0));
            }
        }
        private void translateDirection(AffineTransform transform, int i) {
            if (direction == 0) {
                transform.setToTranslation(200 + i, getWidth() / 3 - i);
            } else if (direction == 1) {
                transform.setToTranslation(200 + i, getWidth() / 3 + i);
            } else if (direction == 2) {
                transform.setToTranslation(200 - i, getWidth() / 3 + i);
            } else if (direction == 3) {
                transform.setToTranslation(200 - i, getWidth() / 3 - i);
            }
        }
    }


}