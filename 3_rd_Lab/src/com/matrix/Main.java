package com.matrix;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class Main
{
    public static boolean determinantNotNull(double[][]matrix)
    {
        int i = 0;
        while(i != matrix.length)
        {
            if(matrix[i][i] == 0)
            {
                return false;
            }
            i++;
        }
        return true;
    };
    public static boolean isMatrixUpDiagonal(double[][] matrix) {
        for(int i = 0;i < matrix.length;i++) {
            for (int j = 0;j < i;j++) {
                if(matrix[i][j] != 0.0) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args)
    {

        double[] x;
        double[] b = null;
        double[][] matrix = null;
        final double[][] defaultMatrix = {{1,1,1},{0,1,1},{0,0,1}};
        final double[] defaultB = {1,1,1};

        String filename = "input.txt";
        try
        {
            ReadFromFile readFromFile = new ReadFromFile(filename);
            matrix = readFromFile.getMatrix();
            b = readFromFile.getCoef();
            if(!determinantNotNull(matrix)) {
                throw new MyException("Determinant is NULL, data will be replaced by default");
            }
            if(!isMatrixUpDiagonal(matrix)) {
                throw new MyException("Wrong Matrix, data will be replaced by default");
            }

        }
        catch(MyException e) {
            System.out.println(e.getMessage());
            matrix = defaultMatrix;
            b = defaultB;
        }

        catch (FileNotFoundException e) {
            System.out.println("File not found:" + e.getMessage() + " , data will be replaced by default");
            matrix = defaultMatrix;
            b = defaultB;
        }
        catch (InputMismatchException e) {
            System.out.println("Wrong Input" + " , data will be replaced by default");
            matrix = defaultMatrix;
            b = defaultB;
        }
        catch (NegativeArraySizeException e) {
            System.out.println("Wrong Input:" + e.getMessage() + " , data will be replaced by default");
            matrix = defaultMatrix;
            b = defaultB;
        }
        catch (NotEnoughDataException e) {
            System.out.println("Wrong Input:" + e.getMessage() + " , data will be replaced by default");
            matrix = defaultMatrix;
            b = defaultB;
        }

        Solvation solvation = new Solvation(b,matrix);

        System.out.println("Matrix:");
        solvation.printMatrix();

        System.out.println("Vector b:");
        solvation.printCoef();

        x = solvation.solve();

        System.out.println("The Answer:");
        for(int i = 0;i < x.length;i++)
        {
            System.out.println(x[i]);
        }


    }
}
