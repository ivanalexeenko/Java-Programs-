package com.eigenvalues;

import java.util.StringTokenizer;

public class Main {
    public static void main(String[]args) {
        double[][]matrix = { { 2.8 , 1, 1.8 } , { 1 , 3.3 , 1.8} , { 1.8 , 1.8 , 3.8 } }; // Матрица А
        double[] xCurrent = { 1 , 0 , 0}; // Текущий вектор Х
        double[] xNext = { 0 , 0 , 0 }; // Следующий вектор Х(нужен для сравнения)
        double lambdaOneCurrent; // Текущее значение лямбда
        double lambdaOneNext; // Следующее значение лямбда (нужно для проверки Эпсилон)
        double epsilon = 0.0005; // Наш Эпсилон
        double norm = 0; // переменная для вычисления нормы матрицы

        double buffer = 0;

        int n = matrix[0].length;


        // Первая Итерация
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                buffer +=(matrix[i][j] * xCurrent[j]);
            }
            xNext[i] = buffer;
            buffer = 0;
        }
        // Текущее лямбда
        lambdaOneCurrent = xNext[0] / xCurrent[0];
        // Текущий вектор Х - это теперь Следующий вектор
        for(int i = 0;i < xCurrent.length;i++) {
            xCurrent[i] = xNext[i];
        }
        // Вторая Итерация
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                buffer +=(matrix[i][j] * xCurrent[j]);
            }
            xNext[i] = buffer;
            buffer = 0;
        }
        // Следующее лямбда
        lambdaOneNext = xNext[0] / xCurrent[0];

        for(int i = 0;i < xCurrent.length;i++) {
            xCurrent[i] = xNext[i];
        }
        // Переменная для проверки Эпсилона
        double checker = Math.abs(lambdaOneCurrent - lambdaOneNext);
        // Текущее лямбда - теперь следующее
        lambdaOneCurrent = lambdaOneNext;
        // Остальные итерации,выполняем,пока разность не станет меньше либо равна Эпсилон
        while(checker > epsilon) {
            for(int i = 0;i < n;i++) {
                for(int j = 0;j < n;j++) {
                    buffer +=(matrix[i][j] * xCurrent[j]);
                }
                xNext[i] = buffer;
                buffer = 0;
            }
            lambdaOneNext = xNext[0] / xCurrent[0];
            for(int i = 0;i < xCurrent.length;i++) {
                xCurrent[i] = xNext[i];
            }
            checker = Math.abs(lambdaOneCurrent - lambdaOneNext);
            lambdaOneCurrent = lambdaOneNext;
        }
        // Выводим значение лямбда,т.е. собственного значения
        System.out.println("The Biggest Eigenvalue:" + lambdaOneCurrent);

        System.out.println("Normed Eigenvector for the biggest Eigenvalue:");
        // Находим норму Евклида для собственного вектора
        for(int i = 0;i < xCurrent.length;i++) {
            norm += (xCurrent[i] * xCurrent[i]);
        }
        norm = Math.sqrt(norm);
        // Лелим вектор на свою норму,т.е. нормируем его
        for(int i = 0;i < xCurrent.length;i++) {
            xCurrent[i] = xCurrent[i] / norm;
        }
        // Выводим собственный вектор
        for(int i = 0;i < xCurrent.length;i++) {
            System.out.println(xCurrent[i]);
        }


    }
}

