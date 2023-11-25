package backT;

import programRunner.ProgramRunner;

public class BTDriver {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Program takes 1 argument. The filename needed with .txt format");
            System.exit(0);
        }

        String knapProblem = args[0];
        String outputFile = "output3.txt";
        String entriesFile = "entries3.txt";
        ProgramRunner programRunner = new ProgramRunner(knapProblem, outputFile, entriesFile);
        programRunner.btRunner();
    }
}