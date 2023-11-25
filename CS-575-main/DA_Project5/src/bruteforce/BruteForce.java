package bruteforce;

import java.io.*;

public class BruteForce {
    private String knapProbem;
    private String outputFile;
    private int[] profits;
    private int[] weights;
    private int noOfItems;
    private int capacity;
    public BruteForce(String knapProb, String output) {
        knapProbem = knapProb;
        outputFile = output;
    }

    public void readFile(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(knapProbem));
            String line = bufferedReader.readLine();
            String[] lineParts = line.split(" ");
            noOfItems = Integer.parseInt(lineParts[0]);
            capacity = Integer.parseInt(lineParts[1]);
            profits = new int[noOfItems];
            weights = new int[noOfItems];
            int  i = 0;
            while (i < noOfItems){
                String itemLine = bufferedReader.readLine();
                if (itemLine != null) {
                    String[] itemLineParts = itemLine.split(" ");
                    profits[i] = Integer.parseInt(itemLineParts[1]);
                    weights[i] = Integer.parseInt(itemLineParts[2]);
                }
                i++;
            }
            bufferedReader.close();
        } catch (Exception e){
            System.out.println("Error reading from file");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void solve() {
        int totalProfit = 0;
        int totalWeight = 0;
        int totalSet = 0;

        int numberOfItems = weights.length;

        for (int i = 0; i < (1 << numberOfItems); i++) {
            int currentWeight = 0;
            int currentProfit = 0;

            for (int j = 0; j < numberOfItems; j++) {
                if ((i & (1 << j)) > 0) {
                    currentWeight += weights[j];
                    currentProfit += profits[j];
                }
            }

            if (currentWeight <= capacity && currentProfit > totalProfit) {
                totalProfit = currentProfit;
                totalWeight = currentWeight;
                totalSet = i;
            }
        }
        try {
            //File outputFile = new File("output1.txt");
            FileWriter writer = new FileWriter(outputFile);

            writer.write(Integer.bitCount(totalSet) + " ");
            writer.write(totalProfit + " ");
            writer.write(totalWeight + "\n");

            for (int i = 0; i < numberOfItems; i++) {
                if ((totalSet & (1 << i)) > 0) {
                    writer.write("Item " + (i+1) + " ");
                    writer.write(profits[i] + " ");
                    writer.write(weights[i] + "\n");
                }
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing the output to file.");
            e.printStackTrace();
        }
    }
}
