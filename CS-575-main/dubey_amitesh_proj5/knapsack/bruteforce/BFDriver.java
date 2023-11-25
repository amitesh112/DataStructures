package knapsack.bruteforce;

import knapsack.programRunner.ProgramRunner;

public class BFDriver {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Program takes 1 argument. The filename needed with .txt format");
            System.exit(0);
        }

        String knapProblem = args[0];
        String outputFile = "output1.txt";
        ProgramRunner programRunner = new ProgramRunner(knapProblem, outputFile);
        programRunner.bFRunner();
    }
}