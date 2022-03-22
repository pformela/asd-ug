package com.company;

import java.util.Arrays;

public class QuickSort {

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];

        int i = low - 1;

        for(int j = low; j <= high; j++) {
            if(arr[j] < pivot) {
                i++;
                swap(arr, i ,j);
            }
//            System.out.println(Arrays.toString(arr));
        }

        swap(arr, i+1, high);
        return (i+1);
    }

    static void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            int partitioningIndex = partition(arr, low, high);
            quickSort(arr, low, partitioningIndex - 1);
            quickSort(arr, partitioningIndex + 1, high);
        }
    }

    public static void main(String[] args) {
	    int[] arr = {23, 6, 11, 12, 17, 19, 7, 18, 12, 14, 15};

        quickSort(arr, 0, arr.length - 1);
        System.out.println("Result >> "  + Arrays.toString(arr));
    }
}
