//Sudoku.

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Sudoku {
    private int[][] grid;
    private boolean[][] valIsFixed;
    private boolean[][][] subgridHasVal;

    public Sudoku() {
        this.grid = new int[9][9];
        this.valIsFixed = new boolean[9][9];
        this.subgridHasVal = new boolean[3][3][10];
    }

    public void placeVal(int val, int row, int col) {
        this.grid[row][col] = val;
        this.subgridHasVal[row / 3][col / 3][val] = true;
    }

    public void removeVal(int val, int row, int col) {
        this.grid[row][col] = 0;
        this.subgridHasVal[row / 3][col / 3][val] = false;
    }

    public void readConfig(Scanner input) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int val = input.nextInt();
                this.placeVal(val, r, c);
                if (val != 0) {
                    this.valIsFixed[r][c] = true;
                }
            }
            input.nextLine();
        }
    }

    public void printGrid() {
        for (int r = 0; r < 9; r++) {
            this.printRowSeparator();
            for (int c = 0; c < 9; c++) {
                System.out.print("|");
                if (this.grid[r][c] == 0) {
                    System.out.print("   ");
                } else {
                    System.out.print(" " + this.grid[r][c] + " ");
                }
            }
            System.out.println("|");
        }
        this.printRowSeparator();
    }

    private static void printRowSeparator() {
        for (int i = 0; i < 9; i++) {
            System.out.print("----");
        }
        System.out.println("-");
    }

    // Check if placing 'val' at (row, col) is valid
    private boolean isValid(int val, int row, int col) {
        for (int i = 0; i < 9; i++) {
            if (this.grid[row][i] == val || this.grid[i][col] == val) {
                return false;
            }
        }
        return !this.subgridHasVal[row / 3][col / 3][val];
    }

    private boolean solveRB(int n) {
        if (n == 81) return true;

        int row = n / 9;
        int col = n % 9;

        if (this.valIsFixed[row][col]) {
            return solveRB(n + 1);
        }

        for (int val = 1; val <= 9; val++) {
            if (isValid(val, row, col)) {
                placeVal(val, row, col);
                if (solveRB(n + 1)) return true;
                removeVal(val, row, col);
            }
        }
        return false;
    }

    public boolean solve() {
        return this.solveRB(0);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Sudoku puzzle = new Sudoku();

        System.out.print("Enter the name of the puzzle file: ");
        String filename = scan.nextLine();

        try {
            Scanner input = new Scanner(new File(filename));
            puzzle.readConfig(input);
        } catch (IOException e) {
            System.out.println("error accessing file " + filename);
            System.out.println(e);
            System.exit(1);
        }

        System.out.println();
        System.out.println("Here is the initial puzzle: ");
        puzzle.printGrid();
        System.out.println();

        if (puzzle.solve()) {
            System.out.println("Here is the solution: ");
        } else {
            System.out.println("No solution could be found.");
            System.out.println("Here is the current state of the puzzle:");
        }
        puzzle.printGrid();
    }
}
