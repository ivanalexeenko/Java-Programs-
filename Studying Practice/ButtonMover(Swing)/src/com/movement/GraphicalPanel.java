package com.movement;

import javax.swing.*;
import java.awt.*;

public class GraphicalPanel extends JPanel {
    private Font font;
    private Color color;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        font = new Font(Application.FONT_STYLE,Font.ITALIC,Application.FONT_SIZE);
        g.setFont(font);

        color = Color.white;

        g.setColor(color);

        Image background = new ImageIcon(Application.class.getResource(Application.BACKGROUND_IMAGE)).getImage();

        g.drawImage(background,0,0,Application.DEFAULT_WIDTH,Application.DEFAULT_HEIGHT,null);

        int xCenter=(Application.DEFAULT_WIDTH / 2)-(Application.PANEL_TEXT.length()*Application.FONT_SIZE / 2) / 2;

        g.drawString(Application.PANEL_TEXT,xCenter,Application.FONT_SIZE);
        g.drawString(Application.STATUS_TEXT,Application.MOUSE_X_LOC,Application.MOUSE_Y_LOC - Application.FONT_SIZE);



    }

}
