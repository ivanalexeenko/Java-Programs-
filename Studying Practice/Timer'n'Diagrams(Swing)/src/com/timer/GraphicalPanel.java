package com.timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

/**
 * Created by LENOVO on 24.02.2018.
 */
public class GraphicalPanel extends JPanel {
    private Point center;
    private int radius = 200;
    private Font font;
    private Graphics2D graphics2D;
    public static final int FONT_SIZE = 15;
    public static final int FONT_STYLE = Font.BOLD;
    public static final String FONT_NAME = "Bernard MT";
    private Point arrowPosition;
    private BasicStroke stroke;
    private Timer timer;
    private int delay = 1000;
    private double angle;
    private double deltaX;
    private double deltaY;
    private double time = 60;
    private double seconds = 1;
    GraphicalPanel() {
        setSize(Application.WIDTH,Application.HEIGHT);
        center = new Point();
        center.x = Application.WIDTH / 2;
        center.y = Application.HEIGHT / 2;
        font = new Font(FONT_NAME,FONT_STYLE,FONT_SIZE);
        stroke = new BasicStroke(8);

        arrowPosition = new Point((int)center.getX(),(int)center.getY() - radius + (int)stroke.getLineWidth());

        angle = (Math.PI * 2) / time ;
        TimerListener timerListener = new TimerListener();
        timer = new Timer(delay,timerListener);
        setBackground(Color.LIGHT_GRAY);
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D)g;
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.BLUE);
        graphics2D.drawOval(center.x - radius,center.y - radius,2 * radius,2 * radius);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.drawLine(center.x,center.y,arrowPosition.x,arrowPosition.y);

    }
    public class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            arrowPosition.x = (int)(center.x + (radius - stroke.getLineWidth() )* Math.sin(seconds * angle));
            arrowPosition.y = (int)(center.y - (radius - stroke.getLineWidth()) * Math.cos(seconds * angle));
            seconds ++;
            if(seconds == 60) {
                seconds = 0;
            }
            repaint();
        }
    }


}
