package com.arkanoid;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LENOVO on 31.01.2018.
 */
public class Block {
    public void setColor(Color color) {
        this.color = color;
    }

    private ImageIcon image;
    private Color color;
    private Point center;
    private int blockWidth;
    private int blockHeight;
    private boolean isEnabled;

    public Block(Color color, Point center, int blockWidth, int blockHeight, boolean isEnabled) {
        this.color = color;
        this.center = center;
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
        this.isEnabled = isEnabled;
    }

    public Block(ImageIcon image, Color color, Point center, int blockWidth, int blockHeight, boolean isEnabled) {

        this.image = image;
        this.color = color;
        this.center = center;
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
        this.isEnabled = isEnabled;
    }
    public Color getColor() {
        return color;
    }
    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    public boolean isBlockEnabled() {
        return isEnabled;
    }

    public Point getCenter() {
        return center;
    }

    public int getBlockWidth() {
        return blockWidth;
    }

    public int getBlockHeight() {
        return blockHeight;
    }
}
