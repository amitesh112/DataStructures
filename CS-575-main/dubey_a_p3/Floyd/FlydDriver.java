import java.util.*;

public class FlydDriver {
    public static void main(String[] args) {

        /*
            @author: Amitesh Dubey
            @Implementation of Floyd Warshall Algorithm using Dynamic Programming
        */


        Floyd flyd=new Floyd();
        
        /*
        Creating a num * num Matrix
        */ 
        flyd.createMatrix();

        /*
        Finding all Pairs of Shortest Path using Floyd Warshall Algortihm
        */
        flyd.mainAlgorithm();

        /*
        Displaying the Adjacent Matrix and Path Matrix
        */
        flyd.displayMatrix();

        /*
        Displaying Shortest Path Length from each Vertices
        */
        flyd.displayPathVertices();
    }
}