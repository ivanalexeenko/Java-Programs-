package com.arkanoid;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by LENOVO on 31.01.2018.
 */
public class Gameplay extends JPanel {

    enum BlockColors {MAGENTA,RED,YELLOW,BLUE,GREEN,ORANGE,CYAN,GRAY,DARK_GRAY,LIGHT_GRAY};

    private static final int PLATFORM_WIDTH = 100;
    private static final int PLATFORM_HEIGHT = 15;
    private static final int BALL_RADIUS = 10;
    private static final int PLATFORM_GAP = 50;
    private static final int BORDER_SIZE = 15;
    private static final int DELTA_X = 10; // should be positive
    private static final int DELTA_Y = 10; // should be positive

    private Platform platform;
    private Ball ball;


    private static int platformSpeed = 40;
    private static int ballX = 0;
    private static int ballY = 0;
    private static int deltaX = 0;
    private static int deltaY = 0;
    private static Color backgroundColor = Color.BLACK;
    private static Color platformColor = Color.GREEN;
    private static Color ballColor = Color.RED;
    private static Color borderColor = Color.CYAN;
    private boolean isGameStarted = false;
    private Timer timer;
    private int delay = 15;

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

    private ArrayList<Block> blockList;
    private ArrayList<Color> colorList;

    private Random random;


    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;




    public Gameplay() {

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

        BallMover ballMover = new BallMover();
        timer = new Timer(delay, ballMover);
        timer.start();


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
                    if (i * COLUMNS + j < 70) {
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

        g.fillOval(ballX, ballY, Gameplay.BALL_RADIUS * 2, Gameplay.BALL_RADIUS * 2);

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

    }


    private class PlatformMover extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {

                if (!isGameStarted) {
                    isLeftPressed = true;
                    isRightPressed = false;
                }

                platformX = (int) platform.getCenter().getX() - PLATFORM_WIDTH / 2 - BORDER_SIZE;
                if (platformX > 0) {
                    platformX = (int) platform.getCenter().getX() - platformSpeed;
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
                }
                // top border
                else if (new Rectangle(0, 0, bounds.width, BORDER_SIZE).intersects(ballRect)) {
                    deltaY = -deltaY;
                }
                // right border
                else if (new Rectangle(bounds.width - BORDER_SIZE, 0, BORDER_SIZE, bounds.height).intersects(ballRect)) {
                    deltaX = -deltaX;
                }
                // bottom border => game over
                else if (new Rectangle(0, getBounds().height - BORDER_SIZE, bounds.width, BORDER_SIZE).intersects(ballRect)) {
                    isGameStarted = false;
                    isLeftPressed = false;
                    isRightPressed = false;
                    deltaY = DELTA_Y;
                    deltaX = DELTA_X;
                }
                // platform
                else if (new Rectangle((int) platform.getCenter().getX() - PLATFORM_WIDTH / 2,
                        (int) platform.getCenter().getY() - PLATFORM_HEIGHT / 2,
                        PLATFORM_WIDTH, PLATFORM_HEIGHT).intersects(ballRect) && deltaY < 0) {
                    deltaY = -deltaY;
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
}






