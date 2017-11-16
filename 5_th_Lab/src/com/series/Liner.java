package com.series;
public class Liner extends Series{

    private double first;
    private double coefficient;
    private int amount;

    @Override
    public double calculate(int index) {
        if(index < 1) {
            index = 1;
        }
        double result = this.first + this.coefficient * (index - 1);
        return result;
    }

    public Liner(double first, double coefficient) {
        this.first = first;
        this.coefficient = coefficient;
    }



}
