package com.series;
public class Exponential extends Series {

    private double first;
    private double coefficient;
    private int amount;

    @Override
    public double calculate(int index) throws MyException {
        double result = 0.0;
        try {
            if(index <= 0) {
                throw new MyException("Wrong index,index should be more then zero.");
            }
            result = this.first * Math.pow(this.coefficient,(index - 1));
        }
        catch (MyException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public Exponential(double first, double coefficient) {
        this.first = first;
        this.coefficient = coefficient;
    }
}
