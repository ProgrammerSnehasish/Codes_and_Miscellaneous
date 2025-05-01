//NQueen.

import java.util.Scanner;

public class NQueens {   
    private boolean[][] board;
    private boolean[] colEmpty;
    private boolean[] upDiagEmpty;
    private boolean[] downDiagEmpty;
    private int solutionCount;
        
    public NQueens(int n) {
        this.board = new boolean[n][n];
        this.colEmpty = new boolean[n];
        this.upDiagEmpty = new boolean[2 * n - 1];
        this.downDiagEmpty = new boolean[2 * n - 1];
        this.solutionCount = 0;
        
        for (int i = 0; i < 2 * n - 1; i++) {
            if (i < n) {
                this.colEmpty[i] = true;
            }
            this.upDiagEmpty[i] = true;
            this.downDiagEmpty[i] = true;
        }
    }
    
    private void placeQueen(int row, int col) {
        this.board[row][col] = true;
        this.colEmpty[col] = false;
        this.upDiagEmpty[row + col] = false;
        this.downDiagEmpty[(this.board.length - 1) + row - col] = false;
    }
    
    private void removeQueen(int row, int col) {
        this.board[row][col] = false;
        this.colEmpty[col] = true;
        this.upDiagEmpty[row + col] = true;
        this.downDiagEmpty[(this.board.length - 1) + row - col] = true;
    }
    
    private boolean isSafe(int row, int col) {
        return this.colEmpty[col] && this.upDiagEmpty[row + col] && this.downDiagEmpty[(this.board.length - 1) + row - col];
    }
    
    private boolean findSolution(int row, int maxSolutions) {
        if (row == this.board.length) {
            this.solutionCount++;
            this.displayBoard();
            System.out.println();
            return this.solutionCount < maxSolutions;
        } 
        
        for (int col = 0; col < this.board.length; col++) {
            if (this.isSafe(row, col)) {
                this.placeQueen(row, col);
                if (this.findSolution(row + 1, maxSolutions)) {
                    return true;
                }
                this.removeQueen(row, col);
            }
        }
        return false;
    }         

    public boolean findSolution(int maxSolutions) {
        this.solutionCount = 0;
        return this.findSolution(0, maxSolutions);
    }
    
    public void displayBoard() {
        for (int r = 0; r < this.board.length; r++) {
            for (int c = 0; c < this.board.length; c++) {
                System.out.print(this.board[r][c] ? " Q " : " . ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("What is the dimension of the board? ");
        int n = console.nextInt();

        System.out.print("How many solutions do you want to display? (0 for all) ");
        int maxSolutions = console.nextInt();

        NQueens solver = new NQueens(n);

        if (maxSolutions == 0) {
            maxSolutions = Integer.MAX_VALUE;
        }

        if (solver.findSolution(maxSolutions)) {
            System.out.println("Displayed " + solver.solutionCount + " solutions.");
        } else if (solver.solutionCount == 0) {
            System.out.println("Failed to find any solutions!");
        }
    }
}

//This part of code was done by me earlier.

// import java.util.Scanner;
// class NQueen {
//     static int position[];
//     public static boolean queen(int present_queen, int no_of_queens) {
//         if (present_queen > no_of_queens) {
//             print_chessboard(no_of_queens);
//             return true;
//         } else {
//             for (int i = 1; i <= no_of_queens; i++) {
//                 position[present_queen] = i;
//                 if (place(present_queen)) {
//                     if (queen(present_queen + 1, no_of_queens)) {
//                         return true;
//                     }
//                 }
//             }
//         }
//         return false;
//     }
//     public static boolean place(int present_queen) {
//         for (int index = 1; index < present_queen; index++) {
//             if ((position[index] == position[present_queen]) || 
//                 (Math.abs(position[index] - position[present_queen]) == Math.abs(index - present_queen))) {
//                 return false;
//             }
//         }
//         return true;
//     }
//     public static void print_chessboard(int no_of_queens) {
//         System.out.println("Final Chess Board after placing all queens:\n");
//         for (int row = 1; row <= no_of_queens; row++) {
//             for (int column = 1; column <= no_of_queens; column++) {
//                 if (position[row] == column)
//                     System.out.print("Q"+row+"\t");
//                 else
//                     System.out.print("_\t");
//             }
//             System.out.print("\n");
//         }
//     }
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("________N Queen Problem________");
//         System.out.println("Enter total number of queens:");
//         int no_of_queens = scanner.nextInt();
//         position = new int[no_of_queens + 1];
//         if (!queen(1, no_of_queens)) {
//             System.out.println("No solution exists.");
//         }
//     }
// }
