package recursionandbacktracking;

import java.util.*;

public class RatfindPathSolution {

    public static void main(String[] args) {
        RatfindPathSolution solution = new RatfindPathSolution();

        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };

        ArrayList<String> paths = solution.findPath(maze);

        System.out.println("All possible paths from source to destination:");
        for (String path : paths) {
            System.out.println(path);
        }
    }

    public ArrayList<String> findPath(int[][] maze) {

        ArrayList<String> result = new ArrayList<>();

        int n = maze.length;

        // No path if source or destination is blocked
        if (n == 0 || maze[0][0] == 0 || maze[n - 1][n - 1] == 0) {
            return result;
        }

        boolean[][] visited = new boolean[n][n];

        findPaths(
                maze,
                0,
                0,
                visited,
                "",
                result
        );

        return result;
    }

    private void findPaths(
            int[][] maze,
            int row,
            int col,
            boolean[][] visited,
            String path,
            ArrayList<String> result) {

        int n = maze.length;

        // Destination reached
        if (row == n - 1 && col == n - 1) {
            result.add(path);
            return;
        }

        // Mark current cell as visited
        visited[row][col] = true;

        // Down
        if (isSafe(maze, row + 1, col, visited)) {
            findPaths(
                    maze,
                    row + 1,
                    col,
                    visited,
                    path + "D",
                    result
            );
        }

        // Left
        if (isSafe(maze, row, col - 1, visited)) {
            findPaths(
                    maze,
                    row,
                    col - 1,
                    visited,
                    path + "L",
                    result
            );
        }

        // Right
        if (isSafe(maze, row, col + 1, visited)) {
            findPaths(
                    maze,
                    row,
                    col + 1,
                    visited,
                    path + "R",
                    result
            );
        }

        // Up
        if (isSafe(maze, row - 1, col, visited)) {
            findPaths(
                    maze,
                    row - 1,
                    col,
                    visited,
                    path + "U",
                    result
            );
        }

        // Backtrack
        visited[row][col] = false;
    }

    private boolean isSafe(
            int[][] maze,
            int row,
            int col,
            boolean[][] visited) {

        int n = maze.length;

        return row >= 0
                && row < n
                && col >= 0
                && col < n
                && maze[row][col] == 1
                && !visited[row][col];
    }
}
