import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int amount = scanner.nextInt();
        Long[][] coins = new Long[amount][2];
        for(int i = 0;i < amount;i++) {
            coins[i][0] = scanner.nextLong();
            coins[i][1] = scanner.nextLong();
        }

        Long dataMatrix[][][] = new Long[amount][amount][2];

        for(int k = 0;k < amount;k++) {
            dataMatrix[k][k][0] = Long.valueOf(0);
            dataMatrix[k][k][1] = Long.valueOf(0);
        }

        int i = 0;
        int j = 0;
        int displacement = 1;
        Long firstWay,secondWay,thirdWay,fourthWay;
        for(j = 1; j < amount;j++) {
            for(i = 0; i < amount - displacement;i++) {
                firstWay = Math.abs(coins[i + 1][0] - coins[i][0]);
                secondWay = Math.abs(coins[j][0] - coins[i][0]);
                thirdWay = Math.abs(coins[i][0] - coins[j][0]);
                fourthWay = Math.abs(coins[j-1][0] - coins[j][0]);

                Long temp1 =  Math.min(dataMatrix[i + 1][j][0] + firstWay, dataMatrix[i + 1][j][1] + secondWay);
                Long temp2 =  Math.min(dataMatrix[i][j - 1][0] + thirdWay,dataMatrix[i][j - 1][1] + fourthWay);
                if(coins[i][1] < temp1) {
                    temp1 = Long.valueOf(Integer.MAX_VALUE);
                }
                if(coins[j][1] < temp2) {
                    temp2 = Long.valueOf(Integer.MAX_VALUE);
                }

                dataMatrix[i][j][0] = temp1;
                dataMatrix[i][j][1] = temp2;


                j++;
            }
            displacement++;
            j = displacement;
            j--;
        }

        try {
            FileWriter writer = new FileWriter("output.txt");
            Long result = Math.min(dataMatrix[0][amount - 1][0],dataMatrix[0][amount - 1][1]);
            if(result.compareTo(Long.valueOf(Integer.MAX_VALUE)) >= 0) {
                writer.write("No solution");
            }
            else {
                writer.write(result.toString());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


