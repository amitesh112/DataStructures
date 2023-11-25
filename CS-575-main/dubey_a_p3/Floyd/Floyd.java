import java.util.*;
public class Floyd{

    int[][] A;
    int[][] dist;//Shortest Cost
    int[][] P; //Shortest Path
    Random rand=new Random();
    int n=rand.nextInt(6) + 5;//Choosing a random vertex between (5-10)



    /*
    Creating Initial Empty Matrix with random weights between (1-10)
    */
    public void createMatrix(){

        //Getting the randomly selected number
        System.out.println("-----------------------------------------------------");
        System.out.println("Random Number Selected is "+ n);
        System.out.println("");

        A=new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int weight = rand.nextInt(10) + 1;
                A[i][j] = weight;
                A[j][i] = weight;
            }
        }
        P=new int [n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++){
                P[i][j]=0;
            }
        }
    }


    /*
    Implementing Floyd Warshall Algorithm in this Function
    */
    public void mainAlgorithm(){
        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = A[i][j];
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        P[i][j]=k;
                    }
                }
            }
        }
    }


    /*
    Implementing recursive call to print all Shortest Path Length
    */
    public void edges(int u, int v)
    {
        if(P[u][v] != 0){
            List<Integer> path = new ArrayList<>();
            int k = P[u][v];
            while (k != 0) {
                path.add(k + 1);
                k = P[u][k];
            }
            Collections.reverse(path);
            for (int i : path) {
                System.out.print("V" + i + " ");
            }
            edges(P[u][v],v);
        }
    }




    /*
    Function to display the shortest path length
    */
    public void displayPathVertices()
    {
        for(int i = 0; i<n;i++)
        {
            System.out.println("V" +(i+1) + " - Vj: Shortest path length");
            for (int j = 0; j < n; j++) {
                System.out.print("V" +(i+1) + " ");

                edges(i,j);
                System.out.print("V"+(j+1));
                System.out.print(": ");
                System.out.println(dist[i][j]);
            }
            System.out.println("");
        }
    }

    
    /*
    Printing out both the Matrices i.e. Adjacent Matrix and Path Matrix
    */
    public void displayMatrix(){
        System.out.println("AMatrix: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                    System.out.print(" "+A[i][j]);
                
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("PMatrix: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(P[i][j] == 0){
                    System.out.print("0 ");
                }
                else{
                     System.out.print((P[i][j] + 1) + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }


    /*
    Main Function of this class
    */
    public void flyd(){

        int[][] A = new int[n][n];
        
    }
}