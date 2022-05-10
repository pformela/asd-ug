package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


// WARIANT S

@SuppressWarnings("unchecked")
public class Zadanie6_2 {

    public static class HashNode<V> {
        int key;
        V value;

        public HashNode(int key, V value) {
            this.key = key;
            this.value = value;
        }

        public HashNode(int key) {
            this.key = key;
            this.value = null;
        }
    }

    public static class HashTable<V> {
        HashNode<V>[] table;
        int numberOfBuckets;
        int size;
        int currentSize = 0;

        public HashTable(int numberOfBuckets) {
            this.numberOfBuckets = numberOfBuckets;
            this.table = new HashNode[numberOfBuckets];
            this.size = numberOfBuckets;

            for(int i = 0; i < numberOfBuckets; i++) {
                this.table[i] = null;
            }

        }

        private int quadraticProbing(int hashCode, int i) {
            int index = (hashCode + i * i) % this.size;
            return (index > 0) ? index : index * -1;
        }

        private int linearProbing(int hashCode, int i) {
            int index = (hashCode + i) % this.size;
            return (index > 0) ? index : index * -1;
        }

        private int doubleHashing(int hashCode1, int hashCode2, int i) {
            int index = (hashCode1 + i * hashCode2) % this.size;
            return (index > 0) ? index : index * -1;
        }

        private int hashFunc1(Integer key) {
            return key % this.size;
//            return key.hashCode();
        }

        private int hashFunc2(Integer key) {
            return 1 + (key % (this.size - 2));
        }

        public Integer add(Integer key, V value) {
            HashNode<V> newNode = new HashNode<>(key, value);
            int hashCode = hashFunc1(key);
//            System.out.println("HashCode: " + hashCode);
            int bucketNumber;

            for(int i = 0; ; i++) {
                bucketNumber = linearProbing(hashCode, i);
                if(this.table[bucketNumber] == null || (this.table[bucketNumber].key == -1 && this.table[bucketNumber].value == null)) {
                    this.table[bucketNumber] = newNode;
                    this.currentSize++;
                    return key;
                }
            }

//            throw new Exception("HashTable is full");
        }

        public Integer delete(Integer key) {
            int hashCode = hashFunc1(key);
            int bucketNumber;

            for(int i = 0; i < this.size; i++) {
                bucketNumber = linearProbing(hashCode, i);
                if(this.table[bucketNumber] != null && this.table[bucketNumber].key == key) {
                    this.table[bucketNumber] = new HashNode<>(-1);
                    return key;
                }
            }

            return -1;
        }

        public void countPlaceholders() {
            int numberOfPlaceholders = 0;

            for(int i = 0; i < size; i++) {
                if(this.table[i] != null && this.table[i].key == -1)
                    numberOfPlaceholders++;
            }

            System.out.printf("Size: %22s %n", this.size);
            System.out.println("Number of placeholders: " + numberOfPlaceholders);
        }

        public void printTable() {
            for (int i = 0; i < size; i++) {
                if(this.table[i] == null)
                    System.out.println("null");
                else
                    System.out.printf("key: %6s, value: %s %n", this.table[i].key, this.table[i].value);
            }
        }
    }

    public static void main(String[] args) {

        int numberOfBuckets = 20000;

        HashTable<String> newTable = new HashTable<>(numberOfBuckets);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfLinesToRead = (int) (numberOfBuckets * 0.8);
        String currentLine;
        int initialNumberOfLinesToAdd = numberOfLinesToRead / 2;
        Queue<Integer> keysToDelete = new LinkedList<>();

        try {
            while ((currentLine = br.readLine()) != null && numberOfLinesToRead > 0) {

                String[] line = currentLine.split(" ");
                int key = java.lang.Integer.parseInt(line[0]);
                String value = line[1];

                newTable.add(key, value);

                numberOfLinesToRead--;

                if(numberOfLinesToRead % 2 == 1) {
                    keysToDelete.add(key);
                }
            }

//            newTable.printTable();
//            System.out.println();

            while(!keysToDelete.isEmpty()) {
                newTable.delete(keysToDelete.poll());
            }

//            newTable.printTable();

            while((currentLine = br.readLine()) != null && initialNumberOfLinesToAdd > 0) {

                String[] line = currentLine.split(" ");
                int key = java.lang.Integer.parseInt(line[0]);
                String value = line[1];

                newTable.add(key, value);

                initialNumberOfLinesToAdd--;
            }

//            System.out.println();
//            newTable.printTable();

            br.close();
        } catch(Exception e) {
            System.out.println("Caught exception: ");
            e.printStackTrace();
        }

        newTable.countPlaceholders();
    }
}
