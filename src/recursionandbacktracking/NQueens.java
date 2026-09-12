package recursionandbacktracking;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    public static List<List<String>> solveNQueens(int n) {

        List<List<String>> solutions = new ArrayList<>();

        char[][] board = new char[n][n];

        // Initialize board
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                board[row][col] = '.';
            }
        }

        backtrack(board, 0, solutions);

        return solutions;
    }

    private static void backtrack(
            char[][] board,
            int row,
            List<List<String>> solutions) {

        // All queens placed
        if (row == board.length) {
            solutions.add(createBoard(board));
            return;
        }

        // Try every column in current row
        for (int col = 0; col < board.length; col++) {

            if (!isSafe(board, row, col)) {
                continue;
            }

            // Choose
            board[row][col] = 'Q';

            // Explore
            backtrack(board, row + 1, solutions);

            // Backtrack
            board[row][col] = '.';
        }
    }

    private static boolean isSafe(
            char[][] board,
            int row,
            int col) {

        int n = board.length;

        // Check column
        for (int r = 0; r < row; r++) {
            if (board[r][col] == 'Q') {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int r = row - 1, c = col - 1;
             r >= 0 && c >= 0;
             r--, c--) {

            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int r = row - 1, c = col + 1;
             r >= 0 && c < n;
             r--, c++) {

            if (board[r][c] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private static List<String> createBoard(char[][] board) {

        List<String> result = new ArrayList<>();

        for (char[] row : board) {
            result.add(new String(row));
        }

        return result;
    }

    public static void main(String[] args) {

        int n = 4;

        List<List<String>> solutions = solveNQueens(n);

        System.out.println(
                "Number of solutions: " + solutions.size()
        );

        int solutionNumber = 1;

        for (List<String> solution : solutions) {

            System.out.println(
                    "\nSolution " + solutionNumber++ + ":"
            );

            for (String row : solution) {
                System.out.println(row);
            }
        }
    }
}
