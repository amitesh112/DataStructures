
public class LcsDriver {
    public static void main(String[] args) {
        LongestCommonSequence lcs=new LongestCommonSequence();


            /*
            @author: Amitesh Dubey
            @Implementation of Longest Common Sequence using Dynamic Programming
            */


            /*
            Condition to check if we have atleast two Input Strings
            */
            if(args.length<2){
                System.err.println("Error: the program requires at least 2 arguments");
                System.exit(1);
            }

            /*
            Condition to check if any of the string has length
            more than 100.
            */
            if(args[0].length()>100 || args[1].length()>100){
                System.err.println("Length size more than 100");
                System.exit(1);
            }
            /*
            Call the class to perform Longest Common Sequence
            */
            else{
                lcs.lcs(args[0],args[1]);
            }
        
        
    }
}