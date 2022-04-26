package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;


@SuppressWarnings("unchecked")
public class Zadanie6_2 {

    public static class HashNode<K, V> {
        K key;
        V value;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public HashNode() {}
    }

    public static class HashTable<K, V> {
        HashNode<K, V>[] table;
        int numberOfBuckets;
        int size;

        public HashTable(int numberOfBuckets) {
            this.numberOfBuckets = numberOfBuckets;
            this.table = new HashNode[numberOfBuckets];
            this.size = numberOfBuckets;

            for(int i = 0; i < numberOfBuckets; i++) {
                this.table[i] = new HashNode<>();
            }

        }

        private int quadraticAddressing(int hashCode, int i) {
            return (hashCode + i * i) % this.size;
        }

        private int hashFunc(K key) {
            return key.hashCode();
        }

        public K add(K key, V value) throws Exception {
            HashNode<K, V> newNode = new HashNode<>(key, value);
            int hashCode = hashFunc(key);
            int bucketNumber;

            for(int i = 0; i < this.size; i++) {
                bucketNumber = quadraticAddressing(hashCode, i);
                if(this.table[bucketNumber] == null) {
                    this.table[bucketNumber] = newNode;
                    return key;
                }
                System.out.println("elo");
            }

            throw new Exception("HashTable is full");
        }

        public void printTable() {
            for (HashNode<K, V> kvHashNode : this.table) {
                System.out.println(kvHashNode.key);
            }
        }
    }

    public static void main(String[] args) {
        HashTable<Integer, String> newTable = new HashTable<>(20);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String currentLine;
        int numberOfLinesToRead = 20;

        try {
            while ((currentLine = br.readLine()) != null && numberOfLinesToRead > 0) {
                System.out.println("llllll");

                String[] line = currentLine.split(" ");
                int key = Integer.parseInt(line[0]);
                String value = line[1];

                System.out.println("key: " + key + ", value: " + value);

                int addedKey = newTable.add(key, value);

                numberOfLinesToRead--;
            }

            br.close();
        } catch(Exception e) {
            System.out.println("Caught exception: ");
            e.printStackTrace();
        }

        newTable.printTable();
    }
}
