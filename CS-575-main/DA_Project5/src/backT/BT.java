package backT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class BT {
    private String outputFile;
    private int[] profits;
    private int[] weights;
    private int ultimateProfit;
    private int noOfItems;
    private int nodesCount;
    private int capacity;
    private String entriesFile;
    private int[] include;
    private int[] bestChoice;
    private int num;
    private String knapProbem;
    private BufferedWriter bufferedWriterOutput;
    private BufferedWriter bufferedWriterEntries;
    private float globalBound = 0;
    private HashMap<String, int[]> map;
    public BT(String knapProb, String output, String entries){
        knapProbem = knapProb;
        outputFile = output;
        entriesFile = entries;
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
            map = new HashMap<>();
            for (int j = 0; j < noOfItems; j++) {
                String key = "Item"+(j+1);
                map.put(key, new int[]{profits[j], weights[j]});
            }
            bufferedReader.close();
            sortItemsRatio();
        } catch (Exception e){
            System.out.println("Error reading from file");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void sortItemsRatio() {
        Integer[] indices = new Integer[noOfItems];
        for (int i = 0; i < noOfItems; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, new Comparator<Integer>() {
            @Override
            public int compare(Integer i, Integer j) {
                double ratioI = (double) profits[i] / weights[i];
                double ratioJ = (double) profits[j] / weights[j];
                return Double.compare(ratioJ, ratioI);
            }
        });

        int[] newProfit = new int[noOfItems];
        int[] newWeights = new int[noOfItems];
        for (int i = 0; i < noOfItems; i++) {
            int index = indices[i];
            newProfit[i] = profits[index];
            newWeights[i] = weights[index];
        }
        profits = newProfit;
        weights = newWeights;
    }

    public void knapsack(int p,int profitn, int newWeight,int Max, int n, int index ){
        try {
            if (newWeight <= Max && profitn > ultimateProfit) {
                ultimateProfit = profitn;
                num = p;
                for (int j = 1; j <= n; j++) {
                    bestChoice[j] = include[j];
                }
            }
            // bufferedWriterEntries.write(nodesCount+" "+profitn+" "+newWeight+" "+globalBound+"\n");
            // nodesCount++;
            //ultimateProfit = newWeight <= Max && profitn > ultimateProfit ? profitn : ultimateProfit;
            //int num = 0;
            boolean promise = (Promising(p, profitn, Max, newWeight, n)) ;
            //num = newWeight <= Max && profitn > ultimateProfit ? p : num;
            bufferedWriterEntries.write(nodesCount+" "+index+" "+profitn+" "+newWeight+" "+globalBound+"\n");
            nodesCount++;
            index++;
            if(promise) {
                include[p + 1] = 1;
                knapsack(p + 1, profitn + profits[p], newWeight + weights[p], Max, n, index);
                include[p + 1] = 0;
                knapsack(p + 1, profitn, newWeight, Max, n, index);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean Promising(int p, int profitn, int max, int newWeight, int n) {
        //globalBound = profitn;
        if (newWeight >= max) {
            return false;
        }
        float bound = KWF2(p+1, newWeight, profitn, n, max);
        globalBound = bound;
        return (bound > ultimateProfit);
    }

    private float KWF2(int i, int newWeight, int profitn, int n, int max) {
        float bound = profitn;
        float sbx;
        while (newWeight < max && i <= n) {
            if (newWeight + weights[i-1] <= max) {
                newWeight = newWeight + weights[i-1];
                bound = bound + profits[i-1];
            } else {
                sbx = (float) (max - newWeight) / (float) weights[i-1];
                newWeight = max;
                bound = bound +  (profits[i-1] * sbx);
            }
            i += 1;
        }
        return bound;
    }

    public void solve() {
        bestChoice = new int[noOfItems + 1];
        ultimateProfit = 0;
        include = new int[noOfItems+1];
        nodesCount = 1;
        Arrays.fill(include, 0);
        try {
            bufferedWriterOutput = new BufferedWriter(new FileWriter(outputFile));
            bufferedWriterEntries = new BufferedWriter(new FileWriter(entriesFile));
            knapsack(0, 0, 0, capacity, noOfItems, 0);
            int totalCapacity=0;
            int n=0;
            for (int i = 1; i <= num; i++) {
                if (bestChoice[i] == 1) {
                    totalCapacity += weights[i-1];
                    n++;
                }
            }
            bufferedWriterOutput.write(n+" "+ultimateProfit+" "+totalCapacity+"\n");
            for (int i = 1; i <= num; i++) {
                if (bestChoice[i] == 1) {
                    totalCapacity += weights[i-1];
                    int[] searchValue = new int[]{profits[i-1], weights[i-1]};
                    String key = getKeyFromValue(map, searchValue);
                    bufferedWriterOutput.write(key+" "+profits[i-1]+" "+weights[i-1]+"\n");
                }
            }
            bufferedWriterEntries.close();
            bufferedWriterOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    public String getKeyFromValue(HashMap<String, int[]> map, int[] value) {
        for (String key : map.keySet()) {
            if (Arrays.equals(map.get(key), value)) {
                return key;
            }
        }
        return null; // value not found in map
    }

}