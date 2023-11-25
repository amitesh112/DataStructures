package knapsack.createKP;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateProblem{
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Program takes 1 argument. The filename needed with .txt format");
            System.exit(0);
        }
        String fileName = args[0];

        int noOfItems = randGenerator(4, 8);
        int maxWeight = 0;
        int[] profits = new int[noOfItems];
        int[] weights = new int[noOfItems];

        int i = 0;
        while (i<noOfItems) {
            profits[i] = randGenerator(10, 30);
            weights[i] = randGenerator(5, 20);
            maxWeight = maxWeight + weights[i];
            i++;
        }

        int capacity = (int) (Math.floor(0.6 * maxWeight));
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write(noOfItems+" "+capacity+"\n");
            for (int j = 0; j < noOfItems; j++) {
                bufferedWriter.write("Item" + j + " " + profits[j] + " " + weights[j] + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error to write to file");
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static int randGenerator(int min, int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
