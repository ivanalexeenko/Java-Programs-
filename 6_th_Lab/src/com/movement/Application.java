package com.movement;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Application extends JFrame {
    public static final int DEFAULT_WIDTH = 700;
    public static final int DEFAULT_HEIGHT = 500;
    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 50;
    public static final int FONT_SIZE = 20;
    public static final int MOUSE_X_LOC = 100;
    public static final int MOUSE_Y_LOC = 420;

    public static final String DEFAULT_CAPTION = "Button and Mouse movement,it's a kinda magic!";
    public static final String FONT_STYLE = "Algerian";
    public static final String BACKGROUND_IMAGE = "space.jpg";
    public static final String ICON_IMAGE = "gamepad.jpg";
    public static final String PANEL_TEXT = "Move the Asteroidlike Button Thtough the Space...";
    public static final String TEXTURE_IMAGE = "asteroid.jpg";
    public static final String STATUS_TEXT = "Mouse Location:";

    private GraphicalPanel graphicalPanel;
    private JButton button;
    private JTextField location;
    private Font font;
    private String mouseLocationText;
    private Point coordinates = new Point(0,0);
    private StringBuffer buttonText = new StringBuffer();
    private JLabel textField;

    public class CustomMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            coordinates = defineMouseLocation();
            setButtonLocation(button, coordinates.x, coordinates.y,BUTTON_WIDTH,BUTTON_HEIGHT);
            mouseLocationText = "X:" + coordinates.x + ",Y:" + coordinates.y;
            location.setText(mouseLocationText);
        }

    }
    public class CustomMouseMotionAdapter extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            coordinates = defineMouseLocation();
            mouseLocationText = "X:" + coordinates.x + ",Y:" + coordinates.y;
            location.setText(mouseLocationText);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (e.isControlDown()) {
                coordinates = defineMouseLocation();
                setButtonLocation(button, coordinates.x, coordinates.y, BUTTON_WIDTH, BUTTON_HEIGHT);
                mouseLocationText = "X:" + coordinates.x + ",Y:" + coordinates.y;
                location.setText(mouseLocationText);
            }
            else {
                coordinates = defineMouseLocation();
                mouseLocationText = "X:" + coordinates.x + ",Y:" + coordinates.y;
                location.setText(mouseLocationText);
            }
        }
    }
    public class CustomKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            if ((e.getKeyChar() == '\b')) {
                if (buttonText.length() != 0) {
                    buttonText.deleteCharAt(buttonText.length() - 1);
                    textField.setText(buttonText.toString());
                }
            } else if (!e.isActionKey()) {
                int code = e.getKeyCode();
                if (code != KeyEvent.VK_ESCAPE && code != KeyEvent.VK_ALT && code != KeyEvent.VK_SHIFT && code != KeyEvent.VK_CONTROL) {
                    buttonText.append(e.getKeyChar());
                    textField.setText(buttonText.toString());
                }

            }

        }
    }

    Application() {
        super();
        font = new Font(Application.FONT_STYLE,Font.ITALIC,Application.FONT_SIZE);

        setTitle(DEFAULT_CAPTION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setScreenBounds();

        graphicalPanel = new GraphicalPanel();
        graphicalPanel.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        graphicalPanel.setLayout(null);
        setLayout(null);
        add(graphicalPanel);

        Image image = new ImageIcon(Application.class.getResource(Application.ICON_IMAGE)).getImage();
        setIconImage(image);
        ImageIcon buttonTexture = new ImageIcon(Application.class.getResource(Application.TEXTURE_IMAGE));

        button = new JButton();
        textField = new JLabel(buttonText.toString());
        textField.setFont(font);
        textField.setForeground(Color.white);
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setOpaque(false);
        textField.setSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        createButton(button,BUTTON_WIDTH,BUTTON_HEIGHT,buttonTexture);
        JScrollPane.getDefaultLocale();

        button.add(textField);
        addButtonToPanel(button,graphicalPanel);


        mouseLocationText = "X:" + coordinates.x + ",Y:" + coordinates.y;
        location = new JTextField(mouseLocationText);
        createMouseCoordinatesField(location,STATUS_TEXT.length(),FONT_SIZE,font,false,Color.black,JTextField.CENTER,mouseLocationText);
        location.setLocation(MOUSE_X_LOC,MOUSE_Y_LOC);
        graphicalPanel.add(location);

        CustomMouseAdapter customMouseAdapter = new CustomMouseAdapter();
        CustomMouseMotionAdapter customMouseMotionAdapter = new CustomMouseMotionAdapter();
        CustomKeyAdapter customKeyAdapter = new CustomKeyAdapter();

        graphicalPanel.addMouseListener(customMouseAdapter);
        graphicalPanel.addMouseMotionListener(customMouseMotionAdapter);

        button.addMouseListener(customMouseAdapter);
        button.addMouseMotionListener(customMouseMotionAdapter);
        button.addKeyListener(customKeyAdapter);

        location.addMouseListener(customMouseAdapter);
        location.addMouseMotionListener(customMouseMotionAdapter);

    }
    public void setScreenBounds() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width / 2) - DEFAULT_WIDTH / 2;
        int y = (screenSize.height / 2) - DEFAULT_HEIGHT / 2;
        setBounds(x,y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
    public void createButton(JButton button,int width,int height,ImageIcon buttonTexture) {
        Dimension buttonSize = new Dimension(width,height);
        button.setSize(buttonSize);
        button.setIcon(buttonTexture);
    }
    public void addButtonToPanel(JButton button,JPanel panel) {
        Point buttonLocation = new Point(panel.getWidth() / 2 - button.getWidth() / 2,panel.getHeight() / 2 - button.getHeight());
        button.setLocation(buttonLocation);
        graphicalPanel.add(button);
    }
    public void createMouseCoordinatesField(JTextField textField,int wigth,int fontSize,Font font,boolean isEditable,Color foreground,int alignment,String text) {
        textField.setSize(wigth * fontSize / 2,fontSize);
        textField.setFont(font);
        textField.setEditable(isEditable);
        textField.setForeground(foreground);
        textField.setHorizontalAlignment(alignment);
        textField.setText(text);
    }
    public Point defineMouseLocation() {
       int x = MouseInfo.getPointerInfo().getLocation().x - getLocation().x ;
       int y = MouseInfo.getPointerInfo().getLocation().y - getLocation().y;
       return new Point(x,y);
    }
    public void setButtonLocation(JButton button,int x,int y,int width,int height) {
        Point point = new Point(x - width / 2,y - height);
        button.setLocation(point);
    }


    public static void main(String[] args) {
        Application application = new Application();
        application.setVisible(true);
    }

}
