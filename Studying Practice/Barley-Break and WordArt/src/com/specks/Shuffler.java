package com.specks;

import java.util.Random;

public class Shuffler {
    public static int[] shuffle(int[] mas) {
        Random random = new Random();
        for(int i = mas.length - 1;i > 0;i--) {
            int j = random.nextInt(i);
            int temp = mas[i];
            mas[i] = mas[j];
            mas[j] = temp;
        }
        return mas;
    }
}
