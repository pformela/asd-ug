package com.company;

import java.util.Arrays;

public class HeapSort {

    public static int[] buildHeap(int[] arr) {
        int heapSize = arr.length; // 11
        int lastNodeFatherIndex = (heapSize - 2) / 2; // 4

        for(int i = lastNodeFatherIndex; i >= 0; i--)
            arr = heapify(arr, heapSize, i);

        return arr;
    }

    public static int[] heapify(int[] arr, int heapSize, int i) {
        int leftSonIndex = 2 * i + 1;
        int rightSonIndex = 2 * i + 2;
        int largestIndex;

        if(leftSonIndex < heapSize && arr[leftSonIndex] > arr[i])
            largestIndex = leftSonIndex;
        else
            largestIndex = i;

        if(rightSonIndex < heapSize && arr[rightSonIndex] > arr[largestIndex])
            largestIndex = rightSonIndex;

        if(largestIndex != i) {
            int tmp = arr[i];
            arr[i] = arr[largestIndex];
            arr[largestIndex] = tmp;
            heapify(arr, heapSize, largestIndex);
        }

        return arr;
    }

    public static int[] heapSort(int[] arr) {
        arr = buildHeap(arr);
        int heapSize = arr.length;
        int tmp;

        for(int i = heapSize - 1; i >= 1; i--) {
            tmp = arr[0];
            arr[0] = arr[heapSize-1];
            arr[heapSize-1] = tmp;
            heapSize--;
            heapify(arr, heapSize, 0);
        }

        return arr;
    }

    public static void main(String[] args) {
	    int[] arr = {28, 6, 11, 12, 17, 8, 7, 18, 12, 14, 23};


        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(heapSort(arr)));
    }
}
