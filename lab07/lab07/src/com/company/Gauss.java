package com.company;



//@SuppressWarnings("unchecked")
public class Gauss {
    final static int N = 3;
    static boolean gauss(double a[][], double x[]) {
        int max;
        double tmp;
        for(int i = 0; i < N; i++) { // eliminacja
            max = i;
            for(int j = i + 1; j < N; j++) {
                if(Math.abs(a[j][i]) > Math.abs(a[max][i]))
                    max = j;
            }

            for(int k = i; k < N + 1; k++) {
                tmp = a[i][k];
                a[i][k] = a[max][k];
                a[max][k] = tmp;
            }

            if(a[i][i] == 0)
                return false;

            for(int j = i + 1; j < N; j++) {
                for(int k = N; k >= i; k--)
                    a[j][k] = a[j][k] - a[i][k] * a[j][i] / a[i][i];
            }

            for(int j = N - 1; j>= 0; j--) {
                tmp = 0;
                for(int k = j + 1; k < N; k++)
                    tmp = tmp + a[j][k] * x[k];
                x[j] = (a[j][N] - tmp) / a[j][j];
            }
        }
        return true;
    }


    public static void main(String[] args) {
        double x[] = new double[N];
        double a[][] = {{5,0,1,9},
                        {1,1,-1,6},
                        {2,-1,1,0}};

        double as[][] = {{1,1,1,9},
                        {0,1,1,6},
                        {0,1,1,4}};

        if(!gauss(a, x)) {
            System.out.println("Układ a jest sprzeczny.");
        } else {
            System.out.println("Rozwiązanie: ");
            for(int i = 0; i < N; i++)
                System.out.printf("x[%1d]=%2.2f, ", i, x[i]);
        }

    }
}
