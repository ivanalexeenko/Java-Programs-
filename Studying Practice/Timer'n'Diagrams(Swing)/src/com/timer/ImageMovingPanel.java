package com.timer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class ImageMovingPanel extends JPanel {
    private Point center;
    private Graphics2D graphics2D;

    private BufferedImage image;
    private int imageWidth = 25;
    private int imageHeight = 25;
    private Point imagePosition;
    private BasicStroke stroke;
    private Timer timer;
    private int delay = 50;
    private double phi;
    private double angle;
    private double omega = 0;
    private double velocity = 0.1;
    private int radius;

    private double time = 60;
    private double interval = 0;

    private JPanel sliderPanel;
    private BoundedRangeModel boundedRangeModel;
    private JSlider slider;
    private boolean isClockWise = true;
    private boolean isCounterClockWise = false;
    private DefaultListModel listModel;
    private JList list;



    ImageMovingPanel() {
        setLayout(new BorderLayout());
        sliderPanel = new JPanel();
        sliderPanel.setLayout(new BorderLayout());
        angle=0;
        boundedRangeModel = new DefaultBoundedRangeModel(1,0,0,15);
        listModel = new DefaultListModel();
        listModel.add(0,"ClockWise Move");
        listModel.add(1,"CounterClockWise Move");
        list = new JList(listModel);

        slider = new JSlider(boundedRangeModel);


        add(slider,BorderLayout.SOUTH);
        add(sliderPanel,BorderLayout.WEST);
        sliderPanel.add(list,BorderLayout.NORTH);
        sliderPanel.setOpaque(false);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(list.isSelectedIndex(0)) {
                    isClockWise = true;
                    isCounterClockWise = false;
                }
                else if(list.isSelectedIndex(1)) {
                    isClockWise = false;
                    isCounterClockWise = true;
                }
            }
        });

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double value = (  (double) ((JSlider)e.getSource()).getValue()) / 10;
                velocity = value;
                repaint();

            }
        });

        setSize(Application.WIDTH,Application.HEIGHT);
        center = new Point();
        stroke = new BasicStroke(5);

        try {
            try {
                image = ImageIO.read(new File(getClass().getResource("football.png").toURI()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagePosition = new Point();
        TimerListener timerListener = new TimerListener();
        timer = new Timer(delay,timerListener);
        setBackground(Color.GREEN);

        recalculateParameters();

        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D)g;
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.WHITE);

        recalculateParameters();

        graphics2D.drawOval(center.x - radius,center.y - radius,2 * radius,2 * radius);
        if(image != null) {
            graphics2D.drawImage(image,imagePosition.x - imageWidth,imagePosition.y - imageHeight,null);
        }




    }
    public void recalculateParameters() {
        center.x = (int)getSize().getWidth() / 2;
        center.y = (int)getSize().getHeight() / 2;
        radius = (int)Math.min(getSize().getWidth(),getSize().getHeight()) / 2;

        time = 2 * Math.PI * radius / velocity;


        omega = velocity / radius;
        phi = omega * delay;
        if(isClockWise) {
            angle += phi;
        }
        else {
            angle -= phi;
        }

            imagePosition.x = (int)(center.x + (radius) * Math.sin(angle));
            imagePosition.y = (int)(center.y - (radius) * Math.cos(angle));

    }
    public class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            interval ++;
            if(interval == time) {
                interval = 0;
            }
            repaint();
        }
    }


}
