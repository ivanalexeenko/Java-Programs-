package com.specks;

import com.textart.TextPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Application extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 900;
    public static final String CAPTION = "Barley - Break & Mini WordArt";
    public static final String FIRST_CAPTION = "Barley - Break";
    public static final String SECOND_CAPTION = "Mini WordArt";


    private JPanel northPanel;
    private JPanel gamePanel;
    private JPanel buttonPanel;
    private GraphicalPanel fullImagePanel;

    private BufferedImage originalImage;
    private BufferedImage fullImage;
    private BufferedImage breakingImage;


    private String filepath = "src/star_wars_trooper.jpg";
    private int imageWidth = 200;
    private int imageHeight = 200;

    private JButton newGameButton;
    private String newGameButtonText = "New Game";
    private JButton chooseImageButton;
    private String chooseImageButtonText = "Choose Image";
    private JButton textButton;
    private String textButtonText = "<---You Can Get This Image";
    // Only Equal Allowed here
    private static final int BRICK_ROWS = 2;
    private static final int BRICK_COLUMNS = 2;

    private GraphicalPanel[][] panels;
    private BufferedImage[][] images;
    private int[] shuffledIndices;
    private int[][] indices;
    private JDialog barleyBreakDialog;
    private JDialog wordArtDialog;

    private JMenuItem menuItemOne;
    private JMenuBar menuBar;
    private JMenu menuOne;
    private JMenuItem menuItemTwo;
    private JMenu menuTwo;





    public boolean hasSolution(int[] array) {
        int inversions = 0;
        int emptyIndex = Application.BRICK_COLUMNS * Application.BRICK_ROWS - 1;
        for(int i = 1;i < Application.BRICK_ROWS * Application.BRICK_COLUMNS;i++) {
            if(array[i - 1] > array[i]) {
                inversions++;
            }
        }
        return (inversions + emptyIndex) % 2 == 0;
    }

    public void setScreenBounds() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension getScreenSize = toolkit.getScreenSize();
        int x = getScreenSize.width / 2 - WIDTH / 2;
        int y = getScreenSize.height / 2 - HEIGHT / 2;
        setBounds(x,y,WIDTH,HEIGHT);
    }

    public void fillArrays() {
        gamePanel.removeAll();
        gamePanel.repaint();
        try {
            originalImage = ImageIO.read(new File(filepath));

            fullImagePanel.setPreferredSize(new Dimension(imageWidth,imageHeight));
            gamePanel.setPreferredSize(new Dimension(WIDTH,HEIGHT - imageHeight));
            fullImage = ImageResizer.resize(originalImage,imageWidth,imageHeight);
            fullImagePanel.setImage(fullImage);
            fullImagePanel.repaint();
            breakingImage = ImageResizer.resize(originalImage,WIDTH,HEIGHT - imageHeight);

            for(int i = 0;i < BRICK_ROWS * BRICK_COLUMNS;i++) {
                shuffledIndices[i] = i;
            }
            Shuffler.shuffle(shuffledIndices);
            int rowSize = breakingImage.getWidth() / BRICK_ROWS;
            int columnSize = breakingImage.getHeight() / BRICK_COLUMNS;
            int k = 0;
            for(int i = 0;i < BRICK_ROWS;i++) {
                for (int j = 0;j < BRICK_COLUMNS;j++) {
                    images[i][j] = breakingImage.getSubimage(i * rowSize,j * columnSize,rowSize,columnSize);
                    indices[i][j] = shuffledIndices[k];
                    k++;
                }
            }
            for(int i = 0;i < BRICK_ROWS;i++) {
                for (int j = 0;j < BRICK_COLUMNS;j++) {
                    int indexOne = (indices[i][j]) % BRICK_COLUMNS;
                    int indexTwo = (indices[i][j]) / BRICK_COLUMNS;
                    if(indices[i][j] != BRICK_COLUMNS * BRICK_ROWS - 1) {
                        panels[i][j].setPreferredSize(new Dimension(rowSize,columnSize));
                        panels[i][j].setImage(images[indexOne][indexTwo]);
                    }
                    else {
                        panels[i][j].setImage(null);
                    }
                    gamePanel.add(panels[i][j]);
                }
            }

            CustomMouseListener mouseListener = new CustomMouseListener();

            for(int i = 0;i < BRICK_ROWS;i++) {
                for (int j = 0;j < BRICK_COLUMNS;j++) {
                    panels[i][j].addMouseListener(mouseListener);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Image Not Found");



        }


    }

    public Application() {
        setTitle(CAPTION);
        setScreenBounds();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);


        barleyBreakDialog = new JDialog(this);
        barleyBreakDialog.setTitle(FIRST_CAPTION);
        barleyBreakDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        barleyBreakDialog.setResizable(false);
        barleyBreakDialog.setBounds(this.getX() + 10,this.getY() + 10,WIDTH,HEIGHT);
        barleyBreakDialog.setLayout(new BorderLayout());



        northPanel = new JPanel();
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(BRICK_ROWS,BRICK_COLUMNS));
        buttonPanel = new JPanel();
        fullImagePanel = new GraphicalPanel();
        textButton = new JButton(textButtonText);
        textButton.setEnabled(false);
        textButton.setFont(new Font("Bernard MT",Font.BOLD,20));


        newGameButton = new JButton(newGameButtonText);
        chooseImageButton = new JButton(chooseImageButtonText);

        buttonPanel.setLayout(new BorderLayout());
        northPanel.setLayout(new BorderLayout());


        barleyBreakDialog.add(northPanel,BorderLayout.NORTH);
        Border northPanelBorder = BorderFactory.createLineBorder(Color.BLACK,5);
        northPanel.setBorder(northPanelBorder);

        northPanel.add(fullImagePanel,BorderLayout.WEST);
        northPanel.add(buttonPanel,BorderLayout.CENTER);
        buttonPanel.add(newGameButton,BorderLayout.NORTH);
        buttonPanel.add(chooseImageButton,BorderLayout.SOUTH);
        buttonPanel.add(textButton,BorderLayout.CENTER);

        panels = new GraphicalPanel[BRICK_ROWS][BRICK_COLUMNS];
        images = new BufferedImage[BRICK_ROWS][BRICK_COLUMNS];
        indices = new int[BRICK_ROWS][BRICK_COLUMNS];
        shuffledIndices = new int[BRICK_ROWS * BRICK_COLUMNS];
        for(int i = 0;i < BRICK_ROWS;i++) {
            for(int j = 0;j < BRICK_COLUMNS;j++) {
                panels[i][j] = new GraphicalPanel();
            }
        }

        fillArrays();
        barleyBreakDialog.add(gamePanel,BorderLayout.CENTER);
        CustomButtonListener buttonListener = new CustomButtonListener();
        newGameButton.addActionListener(buttonListener);
        chooseImageButton.addActionListener(buttonListener);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuOne = new JMenu("Barley-Break");
        menuTwo = new JMenu("Mini WordArt");
        menuItemOne = new JMenuItem("Launch Game");
        menuItemTwo = new JMenuItem("Show Me!");
        menuBar.add(menuOne);
        menuBar.add(menuTwo);
        menuOne.add(menuItemOne);
        menuTwo.add(menuItemTwo);
        menuItemOne.addActionListener(buttonListener);
        menuItemTwo.addActionListener(buttonListener);

        wordArtDialog = new JDialog(this);
        wordArtDialog.setBounds(0,0,500,500);
        wordArtDialog.setTitle(SECOND_CAPTION);
        TextPanel textPanel = new TextPanel();
        wordArtDialog.add(textPanel);
        wordArtDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        wordArtDialog.setResizable(false);


    }
    public class CustomMouseListener extends MouseAdapter {

        private boolean isOutOfRange(int i,int j) {
            return i < 0 || j < 0 || i >= BRICK_ROWS || j >= BRICK_COLUMNS;
        }
        private Point whereToMove(int i,int j) {
            int indexRow;
            int indexColumn;
            Point point = null;

            indexRow = i - 1;
            indexColumn = j;
            if(!isOutOfRange(indexRow,indexColumn)) {
                if(panels[indexRow][indexColumn].getImage() == null) {
                    point = new Point(indexRow,indexColumn);
                }
            }
            indexRow = i;
            indexColumn = j + 1;
            if(!isOutOfRange(indexRow,indexColumn)) {
                if(panels[indexRow][indexColumn].getImage() == null) {
                    point = new Point(indexRow,indexColumn);
                }
            }
            indexRow = i + 1;
            indexColumn = j;
            if(!isOutOfRange(indexRow,indexColumn)) {
                if(panels[indexRow][indexColumn].getImage() == null) {
                    point = new Point(indexRow,indexColumn);
                }
            }
            indexRow = i;
            indexColumn = j - 1;
            if(!isOutOfRange(indexRow,indexColumn)) {
                if(panels[indexRow][indexColumn].getImage() == null) {
                    point = new Point(indexRow,indexColumn);
                }
            }
            return point;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            GraphicalPanel panel = (GraphicalPanel)e.getSource();
            int row = 0;
            int column = 0;
            boolean found = false;
            for(int i = 0;i < BRICK_ROWS;i++) {
                for(int j = 0;j < BRICK_COLUMNS;j++) {

                    if(panel.equals(panels[i][j])) {
                        row = i;
                        column = j;
                        found = true;
                        break;
                    }

                }
            }
            if(found) {
                for(int i = 0;i < BRICK_ROWS;i++) {
                    for(int j = 0;j < BRICK_COLUMNS;j++) {
                        System.out.println(indices[i][j]);
                    }
                }
                Point point = whereToMove(row,column);
                if(point != null) {
                    panels[point.x][point.y].setImage(panels[row][column].getImage());
                    panels[row][column].setImage(null);
                    panels[row][column].repaint();
                    panels[point.x][point.y].repaint();

                    int temp;
                    temp = indices[point.x][point.y];
                    indices[point.x][point.y] = indices[row][column];
                    indices[row][column] = temp;

                    int k = 0;
                    boolean isOver = true;


                    for (int i = 0;i < BRICK_ROWS;i++) {
                        for(int j = 0;j < BRICK_COLUMNS;j++) {
                            if(indices[i][j] != k) {
                                isOver = false;
                                break;
                            }
                            k++;
                        }
                    }
                    if(isOver) {
                        JOptionPane.showMessageDialog(null,"Congrants, you are the WINNER!!!");
                    }

                }


            }
        }
    }
    public class CustomButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(newGameButton)) {
                fillArrays();
                if(!hasSolution(shuffledIndices) && BRICK_COLUMNS == 4 && BRICK_ROWS == 4) {
                    JOptionPane.showMessageDialog(barleyBreakDialog,"Ha-Ha, Solution Does Not Exist, But You Can Try =)");
                }
            }
            else if(e.getSource().equals(chooseImageButton)) {
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showDialog(null,"Open File");
                if(ret == JFileChooser.APPROVE_OPTION) {
                    filepath = fileChooser.getSelectedFile().getPath();
                    fillArrays();
                    if(!hasSolution(shuffledIndices) && BRICK_COLUMNS == 4 && BRICK_ROWS == 4) {
                        JOptionPane.showMessageDialog(barleyBreakDialog,"Ha-Ha, Solution Does Not Exist, But You Can Try =)");
                    }
                }
            }
            else if(e.getSource().equals(menuItemOne)) {
                barleyBreakDialog.setVisible(true);
                if(!hasSolution(shuffledIndices) && BRICK_COLUMNS == 4 && BRICK_ROWS == 4) {
                    JOptionPane.showMessageDialog(barleyBreakDialog,"Ha-Ha, Solution Does Not Exist, But You Can Try =)");
                }
            }
            else if(e.getSource().equals(menuItemTwo)) {
                wordArtDialog.setVisible(true);
            }
        }
    }



}

