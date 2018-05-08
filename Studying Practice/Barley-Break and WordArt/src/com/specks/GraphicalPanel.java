package com.specks;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicalPanel extends JPanel {
    private BufferedImage image;
    public BufferedImage getImage() {
        return image;
    };
    public void setImage(BufferedImage ii) {
        image = ii;
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image != null) {
            g.drawImage(image,0,0,null);
        }

    }
}
