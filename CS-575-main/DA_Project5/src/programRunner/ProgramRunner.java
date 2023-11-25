package programRunner;

import backT.BT;
import bruteforce.BruteForce;
import dP.DP;

import java.io.IOException;

public class ProgramRunner {
    private String knapProbem;
    private String outputFile;
    private String entriesFile;
    public ProgramRunner(String knapProb, String output){
        knapProbem = knapProb;
        outputFile = output;
    }

    public ProgramRunner(String knapProb, String output, String entries){
        knapProbem = knapProb;
        outputFile = output;
        entriesFile = entries;
    }

    public void bFRunner()  {
        BruteForce bruteForce = new BruteForce(knapProbem, outputFile);
        bruteForce.readFile();
        bruteForce.solve();
    }

    public void dpRunner(){
        DP dp = null;
        try {
            dp = new DP(knapProbem, outputFile, entriesFile);
            dp.readFile();
            dp.solve();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btRunner(){
        BT bt = new BT(knapProbem, outputFile, entriesFile);
        bt.readFile();
        bt.solve();
    }
}
