package com.series;

import javax.swing.*;
import java.awt.*;



public class GraphicalPanel extends JPanel {

    public void paintComponent(Graphics g){
        super.paintComponent(g);


        Font font=new Font(Application.FONT_STYLE,Font.ITALIC,Application.FONT_SIZE);

        g.setFont(font);

        g.setColor(Color.white);


        Image background=new ImageIcon(Application.class.getResource(Application.BACKGROUND_IMAGE)).getImage();

        g.drawImage(background,0,0,Application.DEFAULT_WIDTH,Application.DEFAULT_HEIGHT,null);

        int xCenter=(Application.DEFAULT_WIDTH/2)-(Application.SERIES_TYPE.length()*Application.FONT_SIZE/2)/2;

        g.drawString(Application.SERIES_TYPE,xCenter,Application.FONT_SIZE);

    }


}

