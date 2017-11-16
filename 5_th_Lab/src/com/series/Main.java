package com.series;
public class Main {
    static  public void main(String[] args) {
        Liner liner = new Liner(2,5);
        System.out.println(liner.sum(10));
        Exponential exponential = new Exponential(3,2);
        System.out.println(exponential.sum(5));
        Application application = new Application("LALALALAL");
        application.setBounds(100,100,200,200);
        application.setVisible(true);

    }
}
