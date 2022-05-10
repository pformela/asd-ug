package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


@SuppressWarnings("unchecked")
public class Main {

    static class HashTable<K> {
        private final ArrayList<K>[] table;
        private final int numOfBuckets;
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

            ArrayList<K> currentBucket = table[bucketIndex];

            for(int i = 0; i < currentBucket.size(); i++) {
                K currItem = currentBucket.get(i);
                if(currItem.equals(value)) {
                    if(hashCode(currItem) == hashCode)
                        return currItem;
                }
            }

            return null;
        }

        public void add(K value) {
            int bucketIndex = getBucketIndex(value);

            ArrayList<K> bucket = table[bucketIndex];

            bucket.add(value);
            currentSize++;
        }

        public void printTable() {
            for (ArrayList<K> ks : table) {
                System.out.println(ks);
            }
        }

        public int size() {
            return currentSize;
        }

        public int hashCode(K element) {
            return convertToNumber2(element) % numOfBuckets;
        }

        private int convertToNumber1(K element) {
            int result = 1;
            if(element instanceof Number)
                return (int) element;
            else if(element instanceof String) {
                for (int i = 0; i < ((String) element).length() - 1; i++)
                    result += 111 * (int) ((String) element).charAt(i);

                result = result * 111 + (int) ((String) element).charAt(((String) element).length() - 1);
            }

            return result;
        }

        private int convertToNumber2(K element) {
            int result = 1;
            if(element instanceof Number)
                return (int) element;
            else if(element instanceof String) {
                result += 111 * (int) ((String) element).charAt(0);

                result = result * 111 + (int) ((String) element).charAt(((String) element).length() - 1);
            }

            return result;
        }

        public void getInfo() {
            int numberOfEmptyBuckets = 0;
            int maxBucketLength = table[0].size();
            int averageLengthOfNonEmptyBuckets = 0;

            for (ArrayList<K> bucket : table) {
                int currentSize = bucket.size();
                if(maxBucketLength < currentSize) maxBucketLength = currentSize;
                if(currentSize != 0) averageLengthOfNonEmptyBuckets += currentSize;
                else numberOfEmptyBuckets++;
            }

            averageLengthOfNonEmptyBuckets = averageLengthOfNonEmptyBuckets / (table.length - numberOfEmptyBuckets);

            System.out.println("Number of empty lists in HashTable: " + numberOfEmptyBuckets);
            System.out.println("Maximum length of a list in HashTable: " + maxBucketLength);
            System.out.println("Average length of non empty list in HashTable: " + averageLengthOfNonEmptyBuckets);
        }
    }

    public static void main(String[] args) {

        int numberOfBuckets = 1024;
        HashTable<String> table = new HashTable<>(numberOfBuckets);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data;

        int numOfKeys = numberOfBuckets * 2;

        try {
            while((data = br.readLine()) != null && numOfKeys > 0 ) {
                table.add(data);
                numOfKeys--;
            }
            br.close();

        } catch (IOException ioe) {
            System.out.println("IO error trying to read file.");
        }

        table.getInfo();

//        table.printTable();
    }
}

// D17
// Number of empty lists in HashTable: 2
// Maximum length of a list in HashTable: 6
// Average length of non empty list in HashTable: 2
//
//        [GCC, useful]
//        [project, Apple, actually]
//        [for, mostly, links]
//        [listed, below, both, like, which, may]
//        [remain]
//        [named]
//        []
//        [here]
//        [GNU, accepts, linked]
//        [most]
//        []
//        [see]
//        [and, the]
//        [Only, version]
//        [gcc, options, are]
//        [symbolic]
//        [compiler, der, same, changed]

// D1031
// Number of empty lists in HashTable: 158
// Maximum length of a list in HashTable: 8
// Average length of non empty list in HashTable: 2

// D1024
// Number of empty lists in HashTable: 155
// Maximum length of a list in HashTable: 10
// Average length of non empty list in HashTable: 2

// W17
// Number of empty lists in HashTable: 3
// Maximum length of a list in HashTable: 5
// Average length of non empty list in HashTable: 2
//        [the, see, Apple]
//        [which]
//        [here, below, for, like]
//        [most, are, remain]
//        [useful]
//        []
//        [options]
//        [mostly, same]
//        []
//        [linked]
//        [symbolic, changed]
//        []
//        [GCC, listed, actually, may]
//        [project, both]
//        [GNU, and, Only, der, named]
//        [gcc, accepts]
//        [compiler, version, links]

// W1031
// Number of empty lists in HashTable: 155
// Maximum length of a list in HashTable: 7
// Average length of non empty list in HashTable: 2

// W1024
// Number of empty lists in HashTable: 141
// Maximum length of a list in HashTable: 8
// Average length of non empty list in HashTable: 2

// S17
// Number of empty lists in HashTable: 0
// Maximum length of a list in HashTable: 6
// Average length of non empty list in HashTable: 2
//        [project, listed, here, mostly, linked, may]
//        [Only, like]
//        [gcc, changed]
//        [the, options, remain, Apple]
//        [for, version]
//        [symbolic]
//        [useful]
//        [see, same]
//        [below, accepts]
//        [named]
//        [and, both]
//        [are, which]
//        [most, der]
//        [GCC]
//        [GNU, actually]
//        [links]
//        [compiler]

// S1031
// Number of empty lists in HashTable: 617
// Maximum length of a list in HashTable: 47
// Average length of non empty list in HashTable: 4

// S1024
// Number of empty lists in HashTable: 626
// Maximum length of a list in HashTable: 44
// Average length of non empty list in HashTable: 5



