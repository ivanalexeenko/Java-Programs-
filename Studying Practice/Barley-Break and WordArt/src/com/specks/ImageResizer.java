package com.specks;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageResizer {
    public static BufferedImage resize(BufferedImage iconToResize,int scaledWidth,int scaledHeight) {
        BufferedImage inputImage = iconToResize;
        BufferedImage outputImage = new BufferedImage(scaledWidth,scaledHeight,inputImage.getType());
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        return outputImage;
    }
}
