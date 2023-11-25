import java.util.*;

public class Prims{
    int vertexCount;
    int [][] Graph;
    int [] parentNodes;
    int [] edgeWeights;
    boolean [] visitedNodes;
    int[][] primMST;
    ArrayList<String> parentSet;
    StringBuilder stringBuilder = new StringBuilder();


     /*
     The Edge class is implemented as a Node in Minimum Spanning Tree Algorithm i.e Similar implementation as  per Kruskals.
    */
    private static class Edge implements Comparable<Edge> {
        int source;
        int destination;
        int weight;
        public Edge(int initialVertex, int destinationVertex, int weightIn){
            source = initialVertex;
            destination = destinationVertex;
            weight = weightIn;
        }
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }


    /**
     * This function generates a minimum spanning tree using Prim's algorithm.
     */
    public void generatePrims() {
        parentNodes = new int[vertexCount];
        edgeWeights = new int[vertexCount];
        visitedNodes = new boolean[vertexCount];
        parentSet = new ArrayList<>();

        Arrays.fill(edgeWeights, Integer.MAX_VALUE);
        Arrays.fill(visitedNodes, false);

        // Use a priority queue to store edges in ascending order of weight.
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(0, 0, 0));

        edgeWeights[0] = 0;

        while (!priorityQueue.isEmpty()) {
            int sourceNode = priorityQueue.peek().source;
            int destNode = priorityQueue.peek().destination;
            int edgeWeight = priorityQueue.peek().weight;
            priorityQueue.remove();

            if (visitedNodes[destNode]) {
                continue;
            }
            visitedNodes[destNode] = true;

            if (edgeWeight != 0) {
                parentSet.add("V" + (sourceNode+1) + "- V" + (destNode+1) + ": \t" + edgeWeight);
                primMST[sourceNode][destNode] = edgeWeight;
                primMST[destNode][sourceNode] = edgeWeight;
            }

            for (int index = 0; index < vertexCount; index++) {
                int weight = Graph[destNode][index];
                if (visitedNodes[index] == false) {
                    priorityQueue.add(new Edge(destNode, index, weight));
                }
            }
        }
    }


    /*
      Function for printing the Prims Adjacency Matrix
     */
    public void printAdjacencyMatrix(){
        System.out.println("\nPrim's matrix:\n");
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                System.out.print(primMST[i][j]+"\t");
            }
            System.out.println();
        }
    }

    /*
      Function for printing the Minimum Spanning Tree.
     */
    public void printMST() {
        System.out.println("\nPrim's MST:");
        for (String s : parentSet) {
            System.out.println(s);
        }
    }


    /*  
      I have created an constructor for the prims class which would take number of vertexes and Graph and then call the respective
      functions which need to be performed.
    */
    public void Prims(int n, int[][] A){
        vertexCount = n;
        Graph = A;

        //Creating v * v size of graph
        primMST = new int[vertexCount][vertexCount];
        parentSet = new ArrayList<>();

        //Function for prims algorithm
        generatePrims();

        //Funtion for printing adjecency matrix
        printAdjacencyMatrix();

        //Printing minimum spanning tree
        printMST();
    }
}