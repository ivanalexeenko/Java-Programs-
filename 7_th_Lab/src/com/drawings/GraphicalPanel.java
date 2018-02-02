package com.drawings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class GraphicalPanel extends JPanel {

    public static Point firstLocation = new Point(0,0);
    public static Point secondLocation = new Point(0,0);
    public static final int PANEL_WIDTH = 1000;
    public static final int PANEL_HEIGHT = 800;
    public static Color color = Color.black;
    public static final int PEN_SIZE = 15;

    private Image image;
    public static BufferedImage screenImage;
    private Graphics2D graphics2D;

    public GraphicalPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        ApplicationMotionListener applicationMotionListener = new ApplicationMotionListener();
        addMouseMotionListener(applicationMotionListener);
        ApplicationListener applicationListener = new ApplicationListener();
        addMouseListener(applicationListener);
    }

    @Override
    protected void paintComponent(Graphics g) {

        if(image == null) {
            image = createImage(PANEL_WIDTH,PANEL_HEIGHT);
            graphics2D = (Graphics2D)image.getGraphics();
            graphics2D.setStroke(new BasicStroke(PEN_SIZE));
        }

        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(PEN_SIZE));
        g.drawImage(image,0,0,null);
        if (screenImage != null) {
            g.drawImage(screenImage,0,0,null);
            graphics2D = (Graphics2D)screenImage.getSubimage(0,0,PANEL_WIDTH,PANEL_HEIGHT).getGraphics();
            graphics2D.setStroke(new BasicStroke(PEN_SIZE));
        }
    }



    public class ApplicationMotionListener extends MouseMotionAdapter {

        @Override
        public void mouseDragged(MouseEvent e) {
            firstLocation.x = secondLocation.x;
            firstLocation.y = secondLocation.y;
            secondLocation.x = e.getX();
            secondLocation.y = e.getY();
            graphics2D.setColor(color);
            graphics2D.drawLine(firstLocation.x,firstLocation.y,secondLocation.x,secondLocation.y);
            repaint();
        }

    }
    public class ApplicationListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            secondLocation.x = e.getX();
            secondLocation.y = e.getY();
        }
    }
}
