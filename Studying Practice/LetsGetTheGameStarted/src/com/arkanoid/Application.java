package com.arkanoid;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by LENOVO on 31.01.2018.
 */
public class Application extends JFrame {

    public static String CAPTION = "Arkanoid: the Game";
    public static int WIDTH = 750;
    public static int HEIGHT = 600;

    private JMenu menu;
    private JMenuBar menuBar;
    private JMenuItem menuItem;

    private JDialog difficultyDialog;
    private Image image;
    private DialogPanel dialogPanel;

    private Font font;
    private int fontSize = 25;
    private String fontName = "Bubble Pixel-7 Dark";

    private JButton amateur;
    private JButton middler;
    private JButton professional;
    private JButton psycho;

    private SoundPlayer soundPlayer;
    private Gameplay gameplay;

    private JPanel panelsPanel;

    private Image gameOverImage;

    public void centralizeWindow(int width,int height) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;
        this.setBounds(x,y,width,height);
    }


    public Application() throws HeadlessException, IOException {

        TimerListener listener = new TimerListener();
        Timer timer = new Timer(500,listener);
        timer.start();



        gameOverImage = ImageIO.read(new File("src/com/arkanoid/images/GameOver.jpg"));
        panelsPanel = new JPanel();
        soundPlayer = new SoundPlayer();
        image = ImageIO.read(new File("src/com/arkanoid/images/arcanoid.jpg"));
        font = new Font(fontName,Font.BOLD,fontSize);
        centralizeWindow(Application.WIDTH,Application.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);


        setTitle(Application.CAPTION);
        gameplay = new Gameplay(0);
        setLayout(new BorderLayout());
        add(panelsPanel,BorderLayout.CENTER);
        panelsPanel.setLayout(new BorderLayout());
        panelsPanel.add(gameplay,BorderLayout.CENTER);

        menu = new JMenu("Settings...");
        menuBar = new JMenuBar();
        menuItem = new JMenuItem("Select Difficulty Level");
        setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(menuItem);

        difficultyDialog = new JDialog();
        difficultyDialog.setTitle("If I were you, I'd better pick a psycho level... =)");
        difficultyDialog.setResizable(false);
        difficultyDialog.setBounds(getBounds());
        difficultyDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        ButtonListener buttonListener = new ButtonListener();
        menuItem.addActionListener(buttonListener);

        dialogPanel = new DialogPanel();
        difficultyDialog.add(dialogPanel);
        difficultyDialog.setBounds(getX() + (Application.WIDTH - image.getWidth(null)) / 2,getY(),image.getWidth(null),image.getHeight(null));

        amateur = new JButton(PlayerInfo.levels[0]);
        middler = new JButton(PlayerInfo.levels[1]);
        professional = new JButton(PlayerInfo.levels[2]);
        psycho = new JButton(PlayerInfo.levels[3]);
        dialogPanel.setLayout(null);

        amateur.setFont(font);
        middler.setFont(font);
        professional.setFont(font);
        psycho.setFont(font);

        dialogPanel.add(amateur);
        dialogPanel.add(middler);
        dialogPanel.add(professional);
        dialogPanel.add(psycho);

        int location = getWidth() / 2 - 300;

        amateur.setBounds(location,250,400,50);
        middler.setBounds(location,350,400,50);
        professional.setBounds(location,450,400,50);

        psycho.setBounds(location,550,400,50);

        amateur.addActionListener(buttonListener);
        middler.addActionListener(buttonListener);
        professional.addActionListener(buttonListener);
        psycho.addActionListener(buttonListener);

    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(Gameplay.gameOver) {
                setVisible(false);
            }
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(menuItem)) {
                difficultyDialog.setVisible(true);
            }
            else {
                int level = 0;
                soundPlayer.playSound(SoundPlayer.NAVIGATION_SOUND);
                if(e.getSource().equals(amateur)) {
                    level = 0;
                }
                else if(e.getSource().equals(middler)) {
                    level = 1;
                }
                else if (e.getSource().equals(professional)) {
                    level = 2;
                }
                else if (e.getSource().equals(psycho)) {
                    level = 3;
                }
                gameplay.setLevel(level);
                difficultyDialog.setVisible(false);

            }



        }
    }


    private class DialogPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image,0,0,null);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("Select Level:",getWidth() / 2 - ("Select Level:".length() * fontSize) / 2,200);
        }
    }

}
