package com.series;
public class Liner extends Series{

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
            result = this.first + this.coefficient * (index - 1);
            return result;
        }

    }

    public Liner(double first, double coefficient) {
        this.first = first;
        this.coefficient = coefficient;
    }

}
