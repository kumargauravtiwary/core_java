package com.collection.dsa;

public class floodfillAlgo {
    
    //Implement flood fill algorithm for an image represented as a 2D array.
    public static void main(String[] args) {
        int[][] image = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int sr = 1; // Starting row
        int sc = 1; // Starting column
        int newColor = 2; // New color to fill

        floodFill(image, sr, sc, newColor);

        // Print the modified image
        for (int[] row : image) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }

    // Flood fill algorithm
    public static void floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) {
            return; // No need to fill if the color is already the same
        }
        dfs(image, sr, sc, oldColor, newColor);
    }

    // Helper function for DFS
    private static void dfs(int[][] image, int i, int j, int oldColor, int newColor) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != oldColor) {
            return;
        }
        image[i][j] = newColor;
        dfs(image, i + 1, j, oldColor, newColor); // Down
        dfs(image, i - 1, j, oldColor, newColor); // Up
        dfs(image, i, j + 1, oldColor, newColor); // Right
        dfs(image, i, j - 1, oldColor, newColor); // Left
    }
}
