package com.company;

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
//                if(table[index] == 0)
            }

            return 0;
        }
    }
}
