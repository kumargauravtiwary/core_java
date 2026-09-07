package ArrayMatrix;

import java.util.HashSet;
import java.util.Set;

public class distinctIslands {
    
    //Find the number of distinct islands in a grid (shape matters, not just count).
    public static void main(String[] args) {
        int[][] grid = {
            {1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1}
        };
        System.out.println("Number of distinct islands: " + numDistinctIslands(grid));
    }
    // Function to count the number of distinct islands in the grid
    public static int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        Set<String> distinctIslands = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder shape = new StringBuilder();
                    dfs(grid, i, j, shape, "o"); // "o" for origin
                    distinctIslands.add(shape.toString());
                }
            }
        }
        return distinctIslands.size();
    }
    private static void dfs(int[][] grid, int i, int j, StringBuilder shape, String string) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0; // Mark as visited
        shape.append(string); // Append the direction to the shape
        dfs(grid, i + 1, j, shape, "d"); // Down
        dfs(grid, i - 1, j, shape, "u"); // Up
        dfs(grid, i, j + 1, shape, "r"); // Right
        dfs(grid, i, j - 1, shape, "l"); // Left
        shape.append("b"); // Backtrack marker
    }

}
