package com.company;

public class QuickSortFest {

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static int partition(int[] arr, int low, int high) {
        System.out.println("elo");
        int pivot = arr[high];

        int i = low - 1;

        for(int j = low; j <= high; j++) {
            if(arr[j] < pivot) {
                i++;
                swap(arr, i ,j);
            }
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
        int[] tab = {6,5,1,15,12,4};
        quickSort(tab, 0, tab.length - 1);
    }
}
