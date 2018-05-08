package com.lalala;

/**
 * Created by LENOVO on 17.02.2018.
 */
public class Country {
    private String name;
    private String capital;
    private String flagPath;


    public String getFlagPath() {
        return flagPath;
    }

    public Country(String name, String capital, String flagPath) {

        this.name = name;
        this.capital = capital;
        this.flagPath = flagPath;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }
}
