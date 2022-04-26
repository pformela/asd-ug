package com.company;

import java.util.Arrays;

public class Main {

    public class HashTable {
        int[] table;
        int numberOfBuckets;
        int size = 0;

        public HashTable(int numberOfBuckets) {
            this.numberOfBuckets = numberOfBuckets;
            this.table = new int[numberOfBuckets];

            for(int i = 0; i < numberOfBuckets; i++) {
                this.table[i] = 0;
            }
        }

        private int hashFunction1(int key) {
            return key % numberOfBuckets;
        }

        private int hashFunction2(int key) {
            return 1 + (key % (numberOfBuckets - 2));
        }

        // A
        private int linearAddressing(int key) {

            int index;

            for(int i = 0; i < numberOfBuckets; i++) {
                index = (hashFunction1(key) + i) % numberOfBuckets;
                if(this.table[index] == 0) return index;
            }

            return -1;
        }

        public int search(int key) {
            int index;

            for(int i = 0; i < numberOfBuckets; i++) {
                index = (hashFunction1(key) + i) % numberOfBuckets;
                if(this.table[index] == key) return key;
            }

            return -1;
        }

        public int delete(int key) {
            int index;

            for(int i = 0; i < numberOfBuckets; i++) {
                index = (hashFunction1(key) + i) % numberOfBuckets;
                if(this.table[index] == key) {
                    int tmp = this.table[index];
                    this.table[index] = -1;
                    return tmp;
                }
            }

            return -1;
        }

        public void printTable() {
            System.out.println(Arrays.toString(this.table));
        }
    }

    public static void main(String[] args) {

    }
}
