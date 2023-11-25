import java.util.*;
public class Main {

    static String[][] board;
    public static void createBoard(int k){
        board = new String[k][k];
        for(int i=0;i<k;i++){
            for(int j=0;j<k;j++){
                board[i][j]="I";
            }
        }
        //Printing the Initial Board
//        for(int i=0;i<k;i++){
//            for(int j=0;j<k;j++){
//                System.out.print(board[i][j] + "");
//            }
//            System.out.println();
//        }
    }

    public static void addDefectCell(int x,int y,int k){
        board[k-y-1][x]="MS";
    }

    public static void tromino(int x_board,int y_board,int x_missing,int y_missing,int k){
        String insert="I";

        int half_size=k/2;
        if(k==2){
            if((x_missing-x_board)<half_size && (y_missing-y_board)<half_size){
                insert="LR";
            }
            else if((x_missing-x_board)<half_size && (y_missing-y_board)>=half_size){
                insert="LL";
            }
            else if((x_missing-x_board)>=half_size && (y_missing-y_board)<half_size){
                insert="UR";
            }
            else if((x_missing-x_board)>=half_size && (y_missing-y_board)>=half_size){
                insert="UL";
            }
            for(int i=0;i<k;i++){
                int yBoardTemp=y_board;
                for(int j=0;j<k;j++){
                    if(board[x_board][yBoardTemp]=="I"){
                        board[x_board][yBoardTemp]=insert;
                    }
                    yBoardTemp++;
                }
                x_board++;
            }
            return;
        }
        int quadrant=0;
        int xUL=x_board + half_size -1;
        int yUL=y_board+half_size-1;
        int xUR=x_board + half_size-1;
        int yUR=y_board + half_size;
        int xLL=x_board + half_size;
        int yLL=y_board + half_size -1;
        int xLR=x_board + half_size;
        int yLR=y_board + half_size;

        if((x_missing-x_board)<half_size && (y_missing-y_board)<half_size){
            quadrant=2;
        } else if ((x_missing-x_board)<half_size && (y_missing-y_board)>=half_size) {
            quadrant=1;
        } else if ((x_missing-x_board)>=half_size && (y_missing-y_board)<half_size) {
            quadrant=3;
        } else if ((x_missing-x_board)>=half_size && (y_missing-y_board)>=half_size) {
            quadrant=4;
        }

        switch(quadrant){
            case 1:

                xUR=x_missing;
                yUR=y_missing;
                if(board[x_board + half_size -1][y_board+half_size-1]!="MS"){
                    board[x_board + half_size -1][y_board+half_size-1]="LL";
                }
                if(board[x_board + half_size][y_board+half_size-1]!="MS"){
                    board[x_board + half_size][y_board+half_size-1]="LL";
                }
                if(board[x_board+half_size][y_board+half_size]!="MS"){
                    board[x_board+half_size][y_board+half_size]="LL";
                }
                break;
            case 2:
                xUL=x_missing;
                yUL=y_missing;
                if(board[x_board+ half_size-1][y_board+half_size]!="MS"){
                    board[x_board+ half_size-1][y_board+half_size]="LR";
                }
                if(board[x_board + half_size][y_board+half_size-1]!="MS"){
                    board[x_board + half_size][y_board+half_size-1]="LR";
                }
                if(board[x_board+half_size][y_board+half_size]!="MS"){
                    board[x_board+half_size][y_board+half_size]="LR";
                }
                break;
            case 3:
                xLL=x_missing;
                yLL=y_missing;
                if(board[x_board + half_size-1][y_board+half_size]!="MS"){
                    board[x_board + half_size-1][y_board+half_size]="UR";
                }
                if(board[x_board+half_size-1][y_board+half_size-1]!="MS"){
                    board[x_board+half_size-1][y_board+half_size-1]="UR";
                }
                if(board[x_board+half_size][y_board+half_size]!="MS"){
                    board[x_board+half_size][y_board+half_size]="UR";
                }
                break;
            case 4:
                xLR=x_missing;
                yLR=y_missing;
                if(board[x_board+half_size-1][y_board+half_size]!="MS"){
                    board[x_board+half_size-1][y_board+half_size]="UL";
                }
                if(board[x_board+half_size-1][y_board+half_size-1]!="MS"){
                    board[x_board+half_size-1][y_board+half_size-1]="UL";
                }
                if(board[x_board+half_size][y_board+half_size-1]!="MS"){
                    board[x_board+half_size][y_board+half_size-1]="UL";
                }
                break;
        }



        tromino(x_board,y_board+half_size,xUR,yUR,half_size);//1st Quadrant
        tromino(x_board,y_board,xUL,yUL,half_size);//2nd Quadrant
        tromino(x_board+half_size,y_board,xLL,yLL,half_size);//3rd Quadrant
        tromino(x_board+half_size,y_board+half_size,xLR,yLR,half_size);//4th Quadrant


    }

    static boolean isPowerOfTwo(int p)
    {
        if (p == 0)
            return false;

        if (p == 1)
            return false;
        while (p != 1) {
            if (p % 2 != 0)
                return false;
            p = p / 2;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Please enter size of board (need to be 2^n and 0 to Quit): ");
        int n=sc.nextInt();
        if(n==0){
            return;
        }
        if(!isPowerOfTwo(n)){
            System.out.println("Not a power of 2");
            return;
        }
        createBoard(n);
        System.out.print("Please enter coordinates of missing square (Seperated by a space): ");
        int x= sc.nextInt();
        int y =sc.nextInt();
        if(x<0 || x>=n){
            System.out.println("Invalid Board Coordinates");
            System.exit(0);
        }
        if(y<0 || y>=n){
            System.out.println("Invalid Board Coordinates");
            System.exit(0);
        }
        addDefectCell(x,y,n);
        tromino(0,0,n -1 - y,x,n);

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}