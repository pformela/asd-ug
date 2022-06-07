package com;


import java.util.Arrays;

public class MaximumSubarray {

    public static int maximalSquare(char[][] matrix) {
        int n = matrix.length + 1;
        int m = matrix[0].length + 1;
        int[][] result = new int[n][m];
        int maxSquare = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(i == 0 || j == 0) result[i][j] = 0;
                else if(matrix[i-1][j-1] == '1') {
                    result[i][j] = 1;
                    maxSquare = Math.max(maxSquare, result[i][j]);
                }
                if((i != 0 && j != 0) && (result[i-1][j-1] != 0 && result[i-1][j] != 0 && result[i][j-1] != 0) && matrix[i-1][j-1] == '1'){
                    result[i][j] = result[i-1][j-1] + 1;
                    maxSquare = Math.max(maxSquare, result[i][j]);
                }
            }
        }

        for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));
        }

        return maxSquare;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'}, {'1','1','1','1','1'},{'1','0','1','1','1'}};
        char[][] matrix2 = {{'1','1','1','1','0'},{'1','1','1','1','0'},{'1','1','1','1','1'},{'1','1','1','1','1'},{'0','0','1','1','1'}};
        System.out.println(maximalSquare(matrix2));
        for (char[] chars : matrix2) {
            System.out.println(Arrays.toString(chars));
        }
    }

}
