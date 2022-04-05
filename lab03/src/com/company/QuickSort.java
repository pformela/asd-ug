package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class QuickSort implements Runnable {

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

    static int[] reverse(int[] arr) {
        int[] result = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            result[arr.length - i - 1] = arr[i];
        }

        return result;
    }

    public void run()  {
        int[] numberOfElements = {4000, 8000, 15000, 30000, 60000, 120000};
        long start, end;

        int[] randomlyGeneratedArray;
        int[] sortedArray;
        int[] arrayWithEqualItems;
        int[] reversedSortedArray;

        for (int elements : numberOfElements) {

            System.out.println("Liczba elementów: " + elements);

            randomlyGeneratedArray = IntStream.generate(() -> new Random().nextInt(100000)).limit(elements).toArray();

            start = System.currentTimeMillis();
            quickSort(randomlyGeneratedArray, 0, randomlyGeneratedArray.length - 1);
            end = System.currentTimeMillis();
            System.out.println("Losowo wygenerowana tablica: " + (end - start) + " ms");


            sortedArray = IntStream.generate(() -> new Random().nextInt(100000)).limit(elements).toArray();
            Arrays.sort(sortedArray);

            start = System.currentTimeMillis();
            quickSort(sortedArray, 0, sortedArray.length - 1);
            end = System.currentTimeMillis();
            System.out.println("Posortowana tablica: " + (end - start) + " ms");


            reversedSortedArray = new int[sortedArray.length];
            System.arraycopy(sortedArray, 0, reversedSortedArray, 0, sortedArray.length);
            reversedSortedArray = reverse(reversedSortedArray);

            start = System.currentTimeMillis();
            quickSort(reversedSortedArray, 0, reversedSortedArray.length - 1);
            end = System.currentTimeMillis();
            System.out.println("Odwrotnie posortowana tablica: " + (end - start) + " ms");


            arrayWithEqualItems = new int[elements];
            Arrays.fill(arrayWithEqualItems, 2);

            start = System.currentTimeMillis();
            quickSort(arrayWithEqualItems, 0, arrayWithEqualItems.length - 1);
            end = System.currentTimeMillis();
            System.out.println("Tablica z jednakowymi elementami: " + (end - start) + " ms");

            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(null, new QuickSort(), "Main", 1<<26).start();
    }
}
