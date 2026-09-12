package recursionandbacktracking;

public class SodukuSolution {

    public static void main(String[] args) {
        SodukuSolution solution = new SodukuSolution();
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '5', '8', '.', '.', '.'},
            {'.', '.', '.', '9', '1', '9', '5', '.', '.'},
            {'.', '.', '.', '.', '7', '.', '.', '.', '.'}
        };
        solution.solveSudoku(board);
              
    }

    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {

        // Find an empty cell
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                if (board[row][col] == '.') {

                    // Try digits 1 to 9
                    for (char digit = '1'; digit <= '9'; digit++) {

                        if (isValid(board, row, col, digit)) {

                            // Choose
                            board[row][col] = digit;

                            // Explore
                            if (solve(board)) {
                                return true;
                            }

                            // Backtrack / Undo
                            board[row][col] = '.';
                        }
                    }

                    // No digit works
                    return false;
                }
            }
        }

        // No empty cells → Sudoku solved
         for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        return true;
    }

    private boolean isValid(
            char[][] board,
            int row,
            int col,
            char digit) {

        // Check row
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == digit) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == digit) {
                return false;
            }
        }

        // Check 3 x 3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;

        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == digit) {
                    return false;
                }
            }
        }

        return true;
    }
}
