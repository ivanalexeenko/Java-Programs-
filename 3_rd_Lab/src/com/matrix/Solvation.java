package com.matrix;

public class Solvation
{
    private double[] b;
    private double[][] matrix;

    public Solvation(double[] b, double[][] matrix)
    {
        this.b = b;
        this.matrix = matrix;
    }
    public double[] solve()
    {
        double[] x = new double[b.length];
        double buffer = 0;
        int xFound = 0;
        x[matrix.length - 1] = b[matrix.length - 1] / matrix[matrix.length - 1][matrix.length - 1];
        xFound++;

        for(int i = matrix.length - 2; i >= 0;i--)
        {
            for(int j = matrix[i].length - 1; j >= matrix[i].length - xFound - 1 ;j--)
            {
                buffer += matrix[i][j] * x[j];
            }
            buffer = b[i] - buffer;
            x[i] = buffer / matrix[i][ matrix[i].length - xFound - 1];
            xFound++;
            buffer = 0;
        }
        return x;
    }
    public void printMatrix()
    {
        for(int i = 0;i < matrix.length;i++)
        {
            for(int j = 0;j < matrix[i].length;j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();

        }
    }
    public void printCoef()
    {
        for(int i = 0;i < b.length;i++)
        {
            System.out.println(b[i] + " ");
        }
    }

}
