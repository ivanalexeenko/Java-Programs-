package com.lalala;



import javax.swing.*;

/**
 * Created by LENOVO on 18.02.2018.
 */
public class Tour {
    private ImageIcon icon;
    private String description;
    private Integer cost;
    private Boolean check;

    public ImageIcon getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCost() {
        return cost;
    }

    public Boolean getCheck() {
        return check;
    }

    public Tour(ImageIcon icon, String description, Integer cost, Boolean check) {

        this.icon = icon;
        this.description = description;
        this.cost = cost;
        this.check = check;
    }

}
