import java.util.*;

public class Kruskals {

    /*
     Initialization of  Variables
    */
    static int [][] graphIn;
    static int v;
    static int [][] kruskalMatrix;
    List<Edge> spanningTree  = new ArrayList<>();
    int source;
    int destination;
    int weight;

    private static class Edge implements Comparable<Edge> {
        int initialVertex;
        int destinationVertex;
        int weight;

        public Edge(int initialVertex, int destinationVertex, int weight) {
            this.initialVertex = initialVertex;
            this.destinationVertex = destinationVertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }


    /*
     Finding the parent nodes of respective vertexes
    */
    private static int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent, parent[vertex]);
        }
        return parent[vertex];
    }

    /*
     Driver Program for Implementing Kruskals Algorithm
    */
    public void KruskalDriver(){
        kruskalMatrix=new int[v][v];
        List<Edge> edges = new ArrayList<>();

        // Step 1: Add all edges to the list
        for (int i = 0; i < v; i++) {
            for (int j = i + 1; j < v; j++) {
                if (graphIn[i][j] != 0) {
                    edges.add(new Edge(i, j, graphIn[i][j]));
                }
            }
        }

        // Step 2: Sort the edges by weight
        Collections.sort(edges);


        // Step 3: Create a disjoint set for each vertex
        int[] parent = new int[v];
        for (int i = 0; i < v; i++) {
            parent[i] = i;
        }

        // Step 4: Iterate over the sorted edges and add them to the minimum spanning tree
        for (Edge edge : edges) {
            int parent1 = find(parent, edge.initialVertex);
            int parent2 = find(parent, edge.destinationVertex);

            if (parent1 != parent2) {
                spanningTree.add(edge);
                parent[parent1] = parent2;
            }
        }

        for(int i=0;i<v;i++){
            for(int j=0;j<v;j++){
                for(Edge e:spanningTree){
                    if(e.initialVertex == i && e.destinationVertex == j){
                        kruskalMatrix[i][j]=e.weight;
                        kruskalMatrix[j][i]=e.weight;
                    }
                }
            }
        }
        

        
    }

    /*
     This Function prints Kruskals Matrix
    */
    public void printKruskalsMatrix(){
        //Print Kruskals Matrix
        System.out.println();
        System.out.println("Kruskal's matrix:");
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                System.out.print(" " + kruskalMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /*
     This Function prints Kruskals Minimum Spanning Tree
    */
    public void printKruskalsMST(){
        System.out.println();
        for (Edge edge : spanningTree) {
            System.out.println("V"+edge.initialVertex + " - " +"V"+ edge.destinationVertex + ": " + edge.weight);
        }
    }

    /*
     Main Function
    */
    public void Kruskals(int n, int [][] A){
        graphIn=A;
        v=n;

        //Applying Kruskals Algorithm with this Function call
        KruskalDriver();

        //Function Call for printing Kruskals Matrix
        printKruskalsMatrix();

        //Function Call for printing Kruskals Minimum Spanning Tree
        printKruskalsMST();
    }
}
