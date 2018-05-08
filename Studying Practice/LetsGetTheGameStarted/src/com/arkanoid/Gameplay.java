package com.arkanoid;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by LENOVO on 31.01.2018.
 */
public class Gameplay extends JPanel {

    enum BlockColors {MAGENTA,RED,YELLOW,BLUE,GREEN,ORANGE,CYAN,GRAY,DARK_GRAY,LIGHT_GRAY};


    private final int PLATFORM_WIDTH = 100;
    private final int PLATFORM_HEIGHT = 15;
    private final int BALL_RADIUS = 10;
    private final int PLATFORM_GAP = 50;
    private final int BORDER_SIZE = 15;
    private final int DELTA_X = 5; // should be positive
    private final int DELTA_Y = 5; // should be positive

    private Platform platform;
    private Ball ball;


    private int platformSpeed;
    private int ballX = 0;
    private int ballY = 0;
    private int deltaX = 0;
    private int deltaY = 0;
    private Color backgroundColor = new Color(25,25,112);
    private Color platformColor = Color.GREEN;
    private Color ballColor = Color.RED;
    private Color borderColor = Color.CYAN;
    private boolean isGameStarted = false;
    private Timer timer;
    private int delay;

    private int platformX = 0;
    private int platformY = 0;
    private Rectangle bounds;

    private boolean isFirstTime = true;

    private int blockWidth = 0;
    private int blockHeight = 0;
    private Rectangle blocksArea;
    private final int BLOCKS_GAP = 5;
    private final int COLUMNS = 10;
    private final int ROWS = 20;
    private int activeBlocks;

    private ArrayList<Block> blockList;
    private ArrayList<Color> colorList;

    private Random random;


    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;

    private int level = 0;
    private int hitPoints = 3;
    private final int All_HIT_POINTS = 3;
    private int hitPointImageSize = 50;

    private Image hitPointImage;
    private Image hitPointEmptyImage;

    private ArrayList<Image> hitPointImages;

    public static boolean gameOver = false;

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setPlatformSpeed(int speed) {
        this.platformSpeed = speed;
    }
    public void setLevel(int level) {
        this.level = level;
        setPlatformSpeed(PlayerInfo.platformSpeeds[level]);
        setDelay(PlayerInfo.delays[level]);

        if(timer == null) {
            timer = new Timer(delay, ballMover);
            timer.start();
        }
        else {
            timer.setDelay(delay);
            timer.restart();
        }


    }

    private BallMover ballMover = new BallMover();

    private Font font;

    private Integer score = 0;
    private final Integer ADDITIONAL_SCORE = 100;
    private SoundPlayer soundPlayer;

    private final int BRICKS_AMOUNT = 50;



    public Gameplay(int lev) {

        activeBlocks = BRICKS_AMOUNT;
        soundPlayer = new SoundPlayer();
        font = new Font("Bubble Pixel-7 Dark",Font.BOLD,hitPointImageSize / 2);
        hitPoints = All_HIT_POINTS;
        try {
            hitPointImage = ImageIO.read(new File("src/com/arkanoid/images/heart.png"));
            hitPointEmptyImage = ImageIO.read(new File("src/com/arkanoid/images/empty heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        hitPointImages = new ArrayList<>();
        for(int i = 0;i < All_HIT_POINTS;i++) {
            hitPointImages.add(hitPointImage);
        }
        setLevel(lev);

        bounds = new Rectangle();
        blocksArea = new Rectangle();
        blockList = new ArrayList<Block>();
        colorList = new ArrayList<Color>();
        Random random = new Random();

        int tempo = random.nextInt(10);

        for (int i = 0; i < ROWS * COLUMNS; i++) {
            if (tempo == BlockColors.BLUE.ordinal()) {
                colorList.add(Color.BLUE);
            }
            else if (tempo == BlockColors.CYAN.ordinal()) {
                colorList.add(Color.CYAN);
            }
            else if (tempo == BlockColors.DARK_GRAY.ordinal()) {
                colorList.add(Color.DARK_GRAY);
            }
            else if (tempo == BlockColors.GRAY.ordinal()) {
                colorList.add(Color.GRAY);
            }
            else if (tempo == BlockColors.LIGHT_GRAY.ordinal()) {
                colorList.add(Color.LIGHT_GRAY);
            }
            else if (tempo == BlockColors.GREEN.ordinal()) {
                colorList.add(Color.GREEN);
            }
            else if (tempo == BlockColors.MAGENTA.ordinal()) {
                colorList.add(Color.MAGENTA);
            }
            else if (tempo == BlockColors.ORANGE.ordinal()) {
                colorList.add(Color.ORANGE);
            }
            else if (tempo == BlockColors.RED.ordinal()) {
                colorList.add(Color.RED);
            }

            else if (tempo == BlockColors.YELLOW.ordinal()) {
                colorList.add(Color.YELLOW);
            }
            else {
                colorList.add(Color.MAGENTA);
            }

            tempo = random.nextInt(10);
        }


        deltaX = DELTA_X;
        deltaY = DELTA_Y;

        platformX = Application.WIDTH / 2;
        platformY = Application.HEIGHT - PLATFORM_GAP - BORDER_SIZE - PLATFORM_HEIGHT / 2;
        platform = new Platform(platformColor, PLATFORM_WIDTH, PLATFORM_HEIGHT, new Point(platformX, platformY));

        ballX = platformX;
        ballY = platformY - PLATFORM_HEIGHT / 2 - BALL_RADIUS;
        ball = new Ball(ballColor, BALL_RADIUS, new Point(ballX, ballY));

        setVisible(true);
        setBackground(backgroundColor);
        setFocusable(true);
        PlatformMover platformMover = new PlatformMover();
        addKeyListener(platformMover);
        addMouseMotionListener(new MotionListener());
    }

    public void paint(Graphics g) {
        super.paint(g);


            g.setColor(backgroundColor);

            bounds = getBounds();

            g.setColor(platformColor);

            if (isFirstTime) {
                platformX = bounds.width / 2;
                platformY = bounds.height - BORDER_SIZE - PLATFORM_GAP - PLATFORM_HEIGHT / 2;
                platform.changePlatformPosition(platformX, platformY);

                blocksArea.setBounds(BORDER_SIZE, BORDER_SIZE, bounds.width - 2 * BORDER_SIZE, platformY - PLATFORM_HEIGHT / 2 - 2 * BALL_RADIUS - BORDER_SIZE);
                blockHeight = (blocksArea.height - (ROWS - 1) * BLOCKS_GAP) / ROWS;
                blockWidth = (blocksArea.width - (COLUMNS - 1) * BLOCKS_GAP) / COLUMNS;

                for (int i = 0; i < ROWS; i++) {
                    for (int j = 0; j < COLUMNS; j++) {
                        Color color = colorList.get(i * COLUMNS + j);
                        Point point = new Point(
                                BORDER_SIZE + BLOCKS_GAP * (j + 1) + blockWidth * j + blockWidth / 2,
                                BORDER_SIZE + BLOCKS_GAP * (i + 1) + blockHeight * i + blockHeight / 2);
                        if (i * COLUMNS + j < BRICKS_AMOUNT) {
                            Block block = new Block(color, point, blockWidth, blockHeight, true);
                            blockList.add(block);
                        } else {
                            Block block = new Block(color, point, blockWidth, blockHeight, false);
                            blockList.add(block);
                        }
                    }
                }

                isFirstTime = false;
            }
            platformX = (int) platform.getCenter().getX() - PLATFORM_WIDTH / 2;
            platformY = (int) platform.getCenter().getY() - PLATFORM_HEIGHT / 2;

            g.fillRect(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);

            g.setColor(ballColor);
            if (!isGameStarted) {
                ballX = (int) platform.getCenter().getX();
                ballY = (int) platform.getCenter().getY() - PLATFORM_HEIGHT / 2 - BALL_RADIUS;
                ball.changeBallPosition(ballX, ballY);
                ballX = (int) ball.getCenter().getX() - BALL_RADIUS;
                ballY = (int) ball.getCenter().getY() - BALL_RADIUS;
            } else {
                ballX = (int) ball.getCenter().getX() - BALL_RADIUS;
                ballY = (int) ball.getCenter().getY() - BALL_RADIUS;
            }

            g.fillOval(ballX, ballY, BALL_RADIUS * 2, BALL_RADIUS * 2);

            g.setColor(borderColor);

            g.fillRect(0, 0, BORDER_SIZE, bounds.height);
            g.fillRect(0, 0, bounds.width, BORDER_SIZE);
            g.fillRect(bounds.width - BORDER_SIZE, 0, BORDER_SIZE, bounds.height);
            g.fillRect(0, bounds.height - BORDER_SIZE, bounds.width, BORDER_SIZE);

            g.setColor(Color.white);

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    Block block = blockList.get(i * COLUMNS + j);
                    if (block.isBlockEnabled()) {
                        g.setColor(block.getColor());
                        g.fillRect((int) block.getCenter().getX() - blockWidth / 2,
                                (int) block.getCenter().getY() - blockHeight / 2,
                                blockWidth, blockHeight);
                    }

                }
            }
            for(int  i = 0;i < All_HIT_POINTS;i++) {
                g.drawImage(hitPointImages.get(i),BORDER_SIZE * 2 + hitPointImageSize * i,Application.HEIGHT - 105 - BORDER_SIZE,hitPointImageSize,hitPointImageSize,null);
            }
            g.setFont(font);
            g.setColor(Color.cyan);
            g.drawString("Score:",BORDER_SIZE * 2 + hitPointImageSize * (All_HIT_POINTS + 2),Application.HEIGHT - BORDER_SIZE * 2 - font.getSize() * 2);
            g.drawString(score.toString(),BORDER_SIZE * 2 + hitPointImageSize * (All_HIT_POINTS + 2) +  "Score:".length() * font.getSize(),Application.HEIGHT - BORDER_SIZE * 2 - font.getSize() * 2);




    }


    private class PlatformMover extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {

                if (!isGameStarted) {
                    isLeftPressed = true;
                    isRightPressed = false;
                }

                platformX = (int) platform.getCenter().getX() - PLATFORM_WIDTH / 2  - BORDER_SIZE;
                if (platformX > 0) {
                    platformX = (int) platform.getCenter().getX() - platformSpeed;
                    platformY = (int) platform.getCenter().getY();
                    platform.changePlatformPosition(platformX, platformY);
                    repaint();
                }
                else {
                    platformX = getWidth() - BORDER_SIZE - PLATFORM_WIDTH / 2;
                    platformY = (int) platform.getCenter().getY();
                    platform.changePlatformPosition(platformX, platformY);
                    repaint();
                }

            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

                if (!isGameStarted) {
                    isLeftPressed = false;
                    isRightPressed = true;
                }
                platformX = (int) platform.getCenter().getX() + PLATFORM_WIDTH / 2 + BORDER_SIZE;
                if (platformX < getBounds().width) {
                    platformX = (int) platform.getCenter().getX() + platformSpeed;
                    platformY = (int) platform.getCenter().getY();
                    platform.changePlatformPosition(platformX, platformY);
                    repaint();
                }
                else {
                    platformX = BORDER_SIZE + PLATFORM_WIDTH / 2;
                    platformY = (int) platform.getCenter().getY();
                    platform.changePlatformPosition(platformX, platformY);
                    repaint();
                }

            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (isLeftPressed || isRightPressed) {
                    isGameStarted = true;
                }

            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT && !isGameStarted) {
                isLeftPressed = false;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !isGameStarted) {
                isRightPressed = false;
            }
        }
    }

    private class BallMover implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isGameStarted) {
                ballX = (int) ball.getCenter().getX() - BALL_RADIUS;
                ballY = (int) ball.getCenter().getY() - BALL_RADIUS;

                Rectangle ballRect = new Rectangle(ballX, ballY, BALL_RADIUS * 2, BALL_RADIUS * 2);

                // left border
                if (new Rectangle(0, 0, BORDER_SIZE, bounds.height).intersects(ballRect)) {
                    deltaX = -deltaX;
                    soundPlayer.playSound(SoundPlayer.WALL_HIT_SOUND);
                }
                // top border
                else if (new Rectangle(0, 0, bounds.width, BORDER_SIZE).intersects(ballRect)) {
                    deltaY = -deltaY;
                    soundPlayer.playSound(SoundPlayer.WALL_HIT_SOUND);
                }
                // right border

                else if (new Rectangle(bounds.width - BORDER_SIZE, 0, BORDER_SIZE, bounds.height).intersects(ballRect)) {
                    deltaX = -deltaX;
                    soundPlayer.playSound(SoundPlayer.WALL_HIT_SOUND);
                }
                // bottom border => game over
                else if (new Rectangle(0, getBounds().height - BORDER_SIZE, bounds.width, BORDER_SIZE).intersects(ballRect)) {
                    isGameStarted = false;
                    isLeftPressed = false;
                    isRightPressed = false;
                    deltaY = DELTA_Y;
                    deltaX = DELTA_X;
                    if(hitPoints != 0) {
                        hitPoints--;
                        hitPointImages.set(hitPoints,hitPointEmptyImage);
                        soundPlayer.playSound(SoundPlayer.MINUS_LIFE_SOUND);
                        repaint();
                    }

                    if(hitPoints == 0) {
                        soundPlayer.playSound(SoundPlayer.LOSE_SOUND);
                        JOptionPane.showMessageDialog(null,"GAME OVER! Your final score is " + score);
                        gameOver = true;
                        setVisible(false);

                    }
                }
                // platform
                else if (new Rectangle((int) platform.getCenter().getX() - PLATFORM_WIDTH / 2,
                        (int) platform.getCenter().getY() - PLATFORM_HEIGHT / 2,
                        PLATFORM_WIDTH, PLATFORM_HEIGHT).intersects(ballRect) && deltaY < 0) {
                    deltaY = -deltaY;
                    soundPlayer.playSound(SoundPlayer.PLATFORM_HIT_SOUND);
                }
                // blocks
                else {
                    for (int i = 0; i < ROWS * COLUMNS; i++) {
                        int x = (int) blockList.get(i).getCenter().getX() - blockWidth / 2;
                        int y = (int) blockList.get(i).getCenter().getY() - blockHeight / 2;
                        Rectangle rectangle = new Rectangle(x, y, blockWidth, blockHeight);
                        if (rectangle.intersects(ballRect) && blockList.get(i).isBlockEnabled()) {
                            deltaY = -deltaY;
                            blockList.get(i).setEnabled(false);
                            soundPlayer.playSound(SoundPlayer.BRICK_HIT_SOUND);
                            score += (int)((double)ADDITIONAL_SCORE * PlayerInfo.multipliers[level]);
                            activeBlocks--;
                            if(activeBlocks == 0) {
                                soundPlayer.playSound(SoundPlayer.WIN_SOUND);
                                JOptionPane.showMessageDialog(null,"Here is the Winner!!! Your Score is " + score);
                                gameOver = true;
                                setVisible(false);

                            }
                        }
                    }

                }

            }


            if (isLeftPressed) {
                ballX = (int) ball.getCenter().getX() - deltaX;
                ballY = (int) ball.getCenter().getY() - deltaY;
                ball.changeBallPosition(ballX, ballY);
                repaint();
            } else if (isRightPressed) {
                ballX = (int) ball.getCenter().getX() + deltaX;
                ballY = (int) ball.getCenter().getY() - deltaY;
                ball.changeBallPosition(ballX, ballY);
                repaint();
            }

            repaint();
        }


    }
    private class MotionListener extends MouseMotionAdapter{
        @Override
        public void mouseMoved(MouseEvent e) {
            int x = e.getX();
            int y = platform.getCenter().y;
            if(x < BORDER_SIZE + PLATFORM_WIDTH / 2) {
                x = BORDER_SIZE + PLATFORM_WIDTH / 2;
            }
            if( x > getWidth() - BORDER_SIZE - PLATFORM_WIDTH / 2) {
                x = getWidth() - BORDER_SIZE - PLATFORM_WIDTH / 2;
            }
            platform.changePlatformPosition(x,y);
        }
    }
}







