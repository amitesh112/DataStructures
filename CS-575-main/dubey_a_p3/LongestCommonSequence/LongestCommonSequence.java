import java.util.*;
public class LongestCommonSequence {
    public void lcs(String text1,String text2){

        /*
        Storing the size of the input string
        */
        int m = text1.length();
        int n = text2.length();
        int[][] Lcs = new int[m+1][n+1];
        char[][] b = new char[m+1][n+1];

        /*
        Store the strings in an Character array
        */
        char[] x = text1.toCharArray();
        char[] y = text2.toCharArray();
 


        /*
        Initialize First row and First Column of Lcs to 0
        */
        for(int i=1;i<=m;i++){
            Lcs[i][0] = 0;
        }
        for(int j=1;j<=n;j++){
            Lcs[0][j] = 0;
        }

        /*
        Dynamic Programming Approach to Iterate over all possible prefixes of x and y
        (Algorithm reffered from Lecture Slides)
        */
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(x[i-1] == y [j-1]){
                    Lcs[i][j] = Lcs[i-1][j-1] + 1;
                    b[i][j] = 'D';
                }
                else{
                    if (Lcs[i-1][j] >= Lcs[i][j-1]){
                        Lcs[i][j] = Lcs[i-1][j];
                        b[i][j] = 'U';
                    }
                    else{
                        Lcs[i][j] = Lcs[i][j-1];
                        b[i][j] = 'L';
                    }
                }
            }
        }

        
        /*
        Printing the Longest Common Sequence
        */
        Map.Entry<String, Integer> lcsValue = commonString(b, text1, m, n);
        System.out.println("Length of the LCS: " + (lcsValue != null ? lcsValue.getValue() : 0));
        System.out.println("LCS :" + (lcsValue != null ? lcsValue.getKey() : ""));
    }



    /*
    This method takes in 4 parameters and uses an Iterator to traverse through strings.
    L: a 2D character array containing the directions of the longest common subsequence (LCS) obtained through dynamic programming.
    x: a string representing one of the input strings.
    p: an integer representing the row index in the L array.
    q: an integer representing the column index in the L array.
    */
    public static Map.Entry<String, Integer> commonString(char[][] L, String x, int p, int q) {
        if (p == 0 || q == 0) {
            return Map.entry("", 0);
        }
        switch (L[p][q]) {
            case 'U':
                return commonString(L, x, p-1, q);
            case 'D':
                Map.Entry<String, Integer> lcs1 = commonString(L, x, p-1, q-1);
                return Map.entry(lcs1.getKey() + x.charAt(p-1), lcs1.getValue() + 1);
            default:
                return commonString(L, x, p, q-1);
        }
    }
}
    

