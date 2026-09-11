package ArrayMatrix;

public class wordsearch {
    //Implement a word search in a 2D board (given a word, check if it exists via adjacentcells).
    public static void main(String[] args) {
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println("Word exists in the board: " + exist(board, word));
    }
    //Implement a word search in a 2D board (given a word, check if it exists via adjacentcells).
    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Helper method for depth-first search
    private static boolean dfs(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }

        // Mark the current cell as visited
        char temp = board[i][j];
        board[i][j] = '#';

        // Explore all four directions
        boolean found = dfs(board, word, i + 1, j, index + 1) ||
                         dfs(board, word, i - 1, j, index + 1) ||
                         dfs(board, word, i, j + 1, index + 1) ||
                         dfs(board, word, i, j - 1, index + 1);

        // Restore the original character
        board[i][j] = temp;

        return found;
    }
}
