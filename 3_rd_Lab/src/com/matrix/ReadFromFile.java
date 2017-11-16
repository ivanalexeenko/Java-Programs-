package com.matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ReadFromFile {
    private double[][] matrix;
    private double[] b;

    public ReadFromFile(String filename) throws FileNotFoundException,NegativeArraySizeException,NotEnoughDataException {


        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
        Scanner scanner = new Scanner(new File(filename));
        int size = (scanner.nextInt());
        if(size <= 0) {
            throw new NegativeArraySizeException("Wrong size input, it should be > 0");
        }
        matrix = new double[size][size];
        b = new double[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(scanner.hasNextDouble()) {

                    matrix[i][j] = scanner.nextDouble();
                }
                else throw new NotEnoughDataException("not enough arguments");

            }
        }

        for (int i = 0; i < size; i++) {
            if(scanner.hasNextDouble()) {
                b[i] = scanner.nextDouble();
            }
            else throw new NotEnoughDataException("not enough arguments");

        }


    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[] getCoef() {
        return b;
    }


}