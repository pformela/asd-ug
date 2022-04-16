package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;


class HashTable<K> {
    private ArrayList<K>[] table;
    private int numOfBuckets;
    private int currentSize;

    public HashTable(int numOfBuckets) {
        this.table = new ArrayList[numOfBuckets];
        this.numOfBuckets = numOfBuckets;
        this.currentSize = 0;

        for(int i = 0; i < numOfBuckets; i++) {
            table[i] = new ArrayList<>();
        }
    }

    public int getBucketIndex(K key) {
        int hashCode = hashCode(key);
        int index = hashCode % numOfBuckets;
        return (index < 0) ? index * -1 : index;
    }

    public K get(K value) {
        int bucketIndex = getBucketIndex(value);
        int hashCode = hashCode(value);

        ArrayList<K> head = table[bucketIndex];

        for(int i = 0; i < head.size(); i++) {
            K currItem = head.get(i);
            if(currItem.equals(value)) {
                if(hashCode(currItem) == hashCode)
                    return currItem;
            }
        }

        return null;
    }

    public void add(K value) {
        int bucketIndex = getBucketIndex(value);
//        int hashCode = hashCode(value);

        ArrayList<K> bucket = table[bucketIndex];

        bucket.add(value);
        currentSize++;
    }

    public void printTable() {
        for (ArrayList<K> ks : table) {
            System.out.println(ks);
        }
    }

    public int hashCode(K element) {
        return convertToNumber(element) % numOfBuckets;
    }

    private int convertToNumber(K element) {
        int result = 1;
        if(element instanceof Integer)
            return (int) element;
        else if(element instanceof String) {
            for (int i = 0; i < ((String) element).length() - 1; i++)
                result += 111 * (int) ((String) element).charAt(i);

            result = result * 111 + (int) ((String) element).charAt(((String) element).length() - 1);
        }

        return result;
    }
}


public class Main {

    public static void main(String[] args) {

        HashTable<String> table = new HashTable<>(10);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data;

        try {
            while((data = br.readLine()) != null) {
                table.add(data);
            }
            br.close();

        } catch (IOException ioe) {
            System.out.println("IO error trying to read file.");
        }
    }
}
