package com.lalala;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LENOVO on 17.02.2018.
 */
public class CountryImagesRenderer extends JLabel implements ListCellRenderer<Country> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Country> list, Country value, int index, boolean isSelected, boolean cellHasFocus) {
        ImageIcon icon = new ImageIcon(getClass().getResource("Flags/" + value.getFlagPath()));
        setIcon(icon);
        setText(value.getName());
        if(isSelected) {
            setForeground(list.getSelectionForeground());
            setBackground(list.getSelectionBackground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
    CountryImagesRenderer() {
        setOpaque(true);
    }
}
