package com.collection.dsa;

import java.util.LinkedList;
import java.util.Queue;

//Find the shortest path in a binary matrix from top-left to bottom-right allowing 8-directional movement.
public class shortestPath {
    public static void main(String[] args) {
        int[][] grid = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 0}
        };
        System.out.println("Shortest path length: " + shortestPathBinaryMatrix(grid));
    }
    //Find the shortest path in a binary matrix from top-left to bottom-right allowing 8-directional movement.
    public static int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1; // Start or end is blocked
        }

        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        int pathLength = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];

                if (x == n - 1 && y == m - 1) {
                    return pathLength; // Reached the bottom-right corner
                }

                for (int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    if (newX >= 0 && newX < n && newY >= 0 && newY < m && grid[newX][newY] == 0 && !visited[newX][newY]) {
                        queue.offer(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            pathLength++;
        }

        return -1; // No path found
    }
}
