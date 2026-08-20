package com.collection.dsa;
//Find the row with the maximum number of 1s in a boolean matrix sorted row-wise.

public class max1s { 
    public static void main(String[] args) {
        boolean[][] matrix = {
            {false, false, true, true},
            {false, true, true, true},
            {true, true, true, true},
            {false, false, false, false}
        };
        System.out.println("Row with maximum 1s: " + findMax1sRow(matrix));
    }   
    public static int findMax1sRow(boolean[][] matrix) {
        int maxRowIndex = -1;
        int maxCount = 0;

        for (int i = 0; i < matrix.length; i++) {
            int count = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                maxRowIndex = i;
            }
        }

        return maxRowIndex;
    }
}