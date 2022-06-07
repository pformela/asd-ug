package com;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Zadanie9_1 {

    static String assembleSequence(String x, String y, int xPos, int yPos, int[][] tab, String result) {
        if(tab[xPos][yPos] == 0) {
            System.out.println(result);
            return result;
        }
        else if(x.charAt(xPos-1) == y.charAt(yPos-1))
            return assembleSequence(x, y, xPos-1, yPos-1, tab, result.concat(Character.toString(x.charAt(xPos-1))));
        return assembleSequence(x, y, xPos-1, yPos, tab, result);
    }

    static List<String> longestSequences(String x, String y, int xPos, int yPos, int[][] tab, List<String> result) {
        if(tab[x.length()][y.length()] > tab[xPos][yPos]) return result;
        else if (tab[x.length()][y.length()] == tab[xPos][yPos] && x.charAt(xPos-1) == y.charAt(yPos-1)) {
            result.add(assembleSequence(x, y, xPos, yPos, tab, ""));
            return result;
        } else {
            return Stream.concat(longestSequences(x, y, xPos-1, yPos, tab, result).stream(),
                            longestSequences(x, y, xPos, yPos-1, tab, result).stream())
                    .collect(Collectors.toList());
        }
    }

    static int lcs(String x, String y) {
        int m = y.length();
        int n = x.length();
        int[][] tab = new int[n+1][m+1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                if(i == 0 || j == 0) tab[i][j] = 0;
                else if(x.charAt(i-1) == y.charAt(j-1)) tab[i][j] = tab[i-1][j-1] + 1;
                else tab[i][j] = Math.max(tab[i-1][j], tab[i][j-1]);
            }
        }

        for (int[] ints : tab) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println(longestSequences(x, y, n, m, tab, new ArrayList<>()));

        return tab[n][m];
    }

    public static void main(String[] args) {
        String x = "abbaac";
        String y = "bacbacba";

        System.out.println(lcs(x, y));
    }
}
