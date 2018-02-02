package com.method;

public class Main {
    public static void main(String[]args) {
        double x = 15;
        x = Math.pow(x,4) + 2.83 * Math.pow(x,3) - 4.5 * x*x - 64*x - 20;
        System.out.println(x);
    }
}
