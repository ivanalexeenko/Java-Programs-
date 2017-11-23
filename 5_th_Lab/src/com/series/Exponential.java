package com.series;
public class Exponential extends Series {

    private double first;
    private double coefficient;
    private int amount;

    @Override
    public double calculate(int index) throws MyException {
        double result = 0.0;
        if(index <= 0) {
            return result;
        }
        else {
            result = this.first * Math.pow(this.coefficient,(double) (index - 1));
            return result;
        }
    }

    public Exponential(double first, double coefficient) {
        this.first = first;
        this.coefficient = coefficient;
    }

}
