package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


// WARIANT S

@SuppressWarnings("unchecked")
public class Zadanie6_2 {

    public static class HashNode<K, V> {
        K key;
        V value;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class HashTable<V> {
        HashNode<String, V>[] table;
        int numberOfBuckets;
        int size;
        int currentSize = 0;

        public HashTable(int numberOfBuckets) {
            this.numberOfBuckets = numberOfBuckets;
            this.table = new HashNode[numberOfBuckets];
            this.size = numberOfBuckets;
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

        private int hashFunc1(String key) {
            return key.hashCode() % this.size;
//            return key.hashCode();
        }

        private int hashFunc2(String key) {
            return 1 + (key.hashCode() % (this.size - 2));
        }

        public String add(String key, V value) {
            HashNode<String, V> newNode = new HashNode<>(key, value);
            int hashCode1 = hashFunc1(key);
            int hashCode2 = hashFunc2(key);
//            System.out.println("HashCode: " + hashCode);
            int bucketNumber;

            for(int i = 0; ; i++) {
                bucketNumber = doubleHashing(hashCode1, hashCode2, i);
                if(this.table[bucketNumber] == null || this.table[bucketNumber].key.equals("del")) {
                    this.table[bucketNumber] = newNode;
                    this.currentSize++;
//                    System.out.printf("Added %s at position %d\n", key, bucketNumber);
                    return key;
                }
            }

//            throw new Exception("HashTable is full");
        }

        public String delete(String key) {
            int hashCode1 = hashFunc1(key);
            int hashCode2 = hashFunc2(key);
            int bucketNumber;

            for(int i = 0; i < this.size; i++) {
                bucketNumber = doubleHashing(hashCode1, hashCode2, i);
                if(this.table[bucketNumber] != null && this.table[bucketNumber].key.equals(key)) {
                    this.table[bucketNumber].key = "del";
                    this.table[bucketNumber].value = null;
                    this.currentSize--;
//                    System.out.printf("Deleted %s from position %d\n", key, bucketNumber);
                    return key;
                }
            }

            return "";
        }

        public void countPlaceholders() {
            int numberOfPlaceholders = 0;

            for(int i = 0; i < size; i++) {
                if(this.table[i] != null && this.table[i].key.equals("del"))
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
                    System.out.printf("key: %s\n", this.table[i]);
            }
        }
    }

    public static void main(String[] args) {

        int numberOfBuckets = 2003;

        HashTable<Integer> newTable = new HashTable(numberOfBuckets);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfLinesToRead = (int) (numberOfBuckets * 0.8);
        String currentLine;
        int initialNumberOfLinesToAdd = numberOfLinesToRead / 2;
        Queue<String> keysToDelete = new LinkedList<>();

        try {
            while ((currentLine = br.readLine()) != null && numberOfLinesToRead > 0) {

                String[] line = currentLine.split(" ");
                Integer value = Integer.parseInt(line[0]);
                String key = line[1];

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
                Integer value = Integer.parseInt(line[0]);
                String key = line[1];

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


// Adresowanie liniowe
// Size:                   2003
// Number of placeholders: 248
// Size:                   5003
// Number of placeholders: 597

// Adresowanie kwadratowe
// Size:                   2003
// Number of placeholders: 263
// Size:                   5003
// Number of placeholders: 636

// Dwukrotne haszowanie
// Size:                   2003
// Number of placeholders: 273
// Size:                   5003
// Number of placeholders: 663


