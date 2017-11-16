package com.series;
public class Exponential extends Series {

    private double first;
    private double coefficient;
    private int amount;

    @Override
    public double calculate(int index) {
        double result = this.first * Math.pow(this.coefficient,(index - 1));
        return result;
    }

    public Exponential(double first, double coefficient) {
        this.first = first;
        this.coefficient = coefficient;
    }
}
