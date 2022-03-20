package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapSortIterative {

    public static int[] buildHeap(int[] arr) {
        int heapSize = arr.length; // 11
        int lastNodeFatherIndex = (heapSize - 2) / 2; // 4

        for(int i = lastNodeFatherIndex; i >= 0; i--)
            arr = heapify(arr, heapSize, i);
        return arr;
    }

    public static int[] heapify(int[] arr, int heapSize, int i) {
        int leftSonIndex;
        int rightSonIndex;
        int largestIndex;

        do {
            leftSonIndex = 2 * i + 1;
            rightSonIndex = 2 * i + 2;

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
                i = largestIndex;
            } else break;

        } while(true);

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
        String number;
        List<Integer> nums = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            while((number = br.readLine()) != null) nums.add(Integer.parseInt(number));
            br.close();

        } catch (IOException ioe) {
            System.out.println("IO error trying to read file.");
        }

        int[] arr = nums.stream().mapToInt(Integer::intValue).toArray();

        System.out.println(Arrays.toString(heapSort(arr)));
    }
}
