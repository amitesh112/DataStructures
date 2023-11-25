import java.util.*;
public class Main {

    /*
      Variable Initialization
    */
    static Random rand=new Random();
    static int n=rand.nextInt(6) + 5;
    static int [][]A;


    /*
     Generating a Random n*n weighted Matrix.
    */
    public static void  RandomMatrix(){
        System.out.println("Randomly Selected Number is : "+n);
        A=new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int weight = rand.nextInt(10)+1 ;
                A[i][j] = weight;
                A[j][i] = weight;
            }
        }
        display();
    }

    /*
      Display function to display the Randomly Generated Matrix.
    */
    public static void display(){
        System.out.println();
        System.out.println("Adjacency Matrix is");
        System.out.println();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(" "+A[i][j]+"\t");
            }
            System.out.println("");
        }
    }

    /*
     Main Function to decide which Algorithm must be implemented.
    */
    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);
        Prims prim=new Prims();
        Kruskals kruskal=new Kruskals();
        RandomMatrix();
        System.out.println();
        System.out.println("Enter your Choice....");
        System.out.println("1. Prim's Algorithm");
        System.out.println("2. Kruskal's Algorithm");
        System.out.println("3. Exit");
        System.out.println();
        int choice=sc.nextInt();
        if(choice == 1){
            prim.Prims(n,A);
        }
        else if(choice == 2){
            kruskal.Kruskals(n,A);
        }
        else{
            System.exit(1);
        }
    }
}