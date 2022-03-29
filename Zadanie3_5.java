package com.company;

import com.sun.tools.javac.Main;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Zadanie3_5 implements Runnable{

    public static void bubbleSort(int[] arr, int start, int end) {
        for(int i = start; i < end; i++) {
            for(int j = start; j < end; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }

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

    static void quickSort(int[] arr, int low, int high, int threshold) {

        while(low < high) {
            if(high - low < threshold) {
                bubbleSort(arr, low, high);
                break;
            } else {
                int partitioningIndex = partition(arr, low, high);

                if(partitioningIndex - low < partitioningIndex - high) {
                    quickSort(arr, low, partitioningIndex - 1, threshold);
                    low = partitioningIndex + 1;
                } else {
                    quickSort(arr, partitioningIndex + 1, high, threshold);
                    high = partitioningIndex - 1;
                }
            }
        }
    }

    static int[] reverse(int[] arr) {
        int[] result = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            result[arr.length - i - 1] = arr[i];
        }

        return result;
    }

    public void run() {
        int[] numberOfElements = {4000, 8000, 15000, 30000, 60000, 120000};
        long start, end;

        int[] randomlyGeneratedArray;
        int[] sortedArray;
        int[] arrayWithEqualItems;
        int[] reversedSortedArray;

        for(int threshold = 5; threshold < 51; threshold += 5){

            System.out.println("-----------------------------------------");
            System.out.println("HYBRID QUICKSORT C = " + threshold + "\n");

            for (int elements : numberOfElements) {

                System.out.println("Liczba elementów: " + elements);

                randomlyGeneratedArray = IntStream.generate(() -> new Random().nextInt(100000)).limit(elements).toArray();
//            System.out.println("Przed >> " + Arrays.toString(randomlyGeneratedArray));

                start = System.currentTimeMillis();
                quickSort(randomlyGeneratedArray, 0, randomlyGeneratedArray.length - 1, threshold);
                end = System.currentTimeMillis();
                System.out.println("Losowo wygenerowana tablica: " + (end - start) + " ms");
//            System.out.println("Po >> " + Arrays.toString(randomlyGeneratedArray) + "\n");


                sortedArray = IntStream.generate(() -> new Random().nextInt(100000)).limit(elements).toArray();
                Arrays.sort(sortedArray);

//            System.out.println("Przed >> " + Arrays.toString(sortedArray));
                start = System.currentTimeMillis();
                quickSort(sortedArray, 0, sortedArray.length - 1, threshold);
                end = System.currentTimeMillis();
                System.out.println("Posortowana tablica: " + (end - start) + " ms");
//            System.out.println("Po >> " + Arrays.toString(sortedArray) + "\n");


                reversedSortedArray = new int[sortedArray.length];
                System.arraycopy(sortedArray, 0, reversedSortedArray, 0, sortedArray.length);
                reversedSortedArray = reverse(reversedSortedArray);
//            System.out.println("Przed >> " + Arrays.toString(reversedSortedArray));

                start = System.currentTimeMillis();
                quickSort(reversedSortedArray, 0, reversedSortedArray.length - 1, threshold);
                end = System.currentTimeMillis();
                System.out.println("Odwrotnie posortowana tablica: " + (end - start) + " ms");
//            System.out.println("Po >> " + Arrays.toString(reversedSortedArray) + "\n");


                arrayWithEqualItems = new int[elements];
                Arrays.fill(arrayWithEqualItems, 2);

//            System.out.println("Przed >> " + Arrays.toString(arrayWithEqualItems));
                start = System.currentTimeMillis();
                quickSort(arrayWithEqualItems, 0, arrayWithEqualItems.length - 1, threshold);
                end = System.currentTimeMillis();
                System.out.println("Tablica z jednakowymi elementami: " + (end - start) + " ms");
//            System.out.println("Po >> " + Arrays.toString(arrayWithEqualItems) + "\n");

                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(null, new Zadanie3_5(), "Main", 1<<26).start();
    }

}
