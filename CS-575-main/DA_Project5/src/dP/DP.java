package dP;

import java.io.*;

public class DP {

    private String outputFile;
    private int[] profits;
    private int[] weights;
    private int noOfItems;
    private int capacity;
    private String entriesFile;
    private String knapProbem;
    private int[][] dpTable;
    private BufferedWriter dpTableWriter;
    private BufferedWriter solutionWriter;
    private boolean[][] included;
    public DP(String knapProb, String output, String entries) throws IOException {
        knapProbem = knapProb;
        outputFile = output;
        entriesFile = entries;
        dpTableWriter = new BufferedWriter(new FileWriter(entriesFile));
        solutionWriter = new BufferedWriter(new FileWriter(outputFile));
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
    public void setPositions(int n, int w){
        if(n<=0 || w<=0)
        {
            return;
        }
        included[n][w] = true;
        setPositions(n-1, w);
        setPositions(n-1, w-weights[n-1]);
    }
    public void solve() throws IOException {
        included = new boolean[noOfItems + 1][capacity + 1];
        dpTable = new int[noOfItems + 1][capacity + 1];
        setPositions(noOfItems, capacity);
        for (int i = 1; i <= noOfItems; i++) {
            dpTable[i][0] = 0;
            for (int w = 1; w <= capacity; w++) {
                if (included[i][w] == true) {
                    if (weights[i-1] <= w && dpTable[i-1][w-weights[i-1]] + profits[i-1] > dpTable[i-1][w]) {
                        dpTable[i][w] = dpTable[i - 1][w - weights[i-1]] + profits[i-1];
                    } else {
                        dpTable[i][w] = dpTable[i - 1][w];
                    }
                } else {
                    dpTable[i][w] = 0;
                }
                dpTableWriter.write(dpTable[i][w] + "\t");
            }
            dpTableWriter.newLine();
        }
        dpTableWriter.close();
        int count = 0;
        int totalWeight = 0;
        int i = noOfItems;
        int w = capacity;
        int max = dpTable[noOfItems][capacity];
        while (i > 0 && w > 0) {
            if (!(max == dpTable[i-1][w])) {
                w -= weights[i - 1];
                totalWeight += weights[i-1];
                max = max -profits[i-1];
                count++;
            }
            i--;
        }
        solutionWriter.write(count + " " + dpTable[noOfItems][capacity]+" "+totalWeight);
        solutionWriter.newLine();
        int k = noOfItems;
        int j = capacity;
        while (k > 0 && j > 0) {
            if (!(max == dpTable[k-1][j])) {
                solutionWriter.write("Item" + k + " " + profits[k - 1] + " " + weights[k - 1]);
                solutionWriter.newLine();
                j -= weights[k - 1];
            }
            k--;
        }
        solutionWriter.close();
    }
}