package com.company;

import java.util.ArrayList;
import java.util.Objects;


class HashNode<K, V> {
    K key;
    V value;
    final int hashCode;
    HashNode<K, V> next;

    public HashNode(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}

class HashTable<K, V> {
    private ArrayList<HashNode<K, V>> table;
    private int numOfBuckets;
    private int currentSize;

    public HashTable(int numOfBuckets) {
        this.table = new ArrayList<>();
        this.numOfBuckets = numOfBuckets;
        this.currentSize = 0;

        for(int i = 0; i < numOfBuckets; i++) {
            table.add(null);
        }
    }

    public int getBucketIndex(K key) {
        int hashCode = hashCode(key);
        int index = hashCode % numOfBuckets;
        return (index < 0) ? index * -1 : index;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);

        HashNode<K, V> head = table.get(bucketIndex);

        while(head != null) {
            if(head.key.equals(key) && head.hashCode == hashCode)
                return head.value;
            head = head.next;
        }

        return null;
    }

    public void add(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);

        HashNode<K, V> head = table.get(bucketIndex);

        while(head != null) {
            if(head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        this.currentSize++;
        head = table.get(bucketIndex);
        HashNode<K, V> newNode = new HashNode<>(key, value, hashCode);

        newNode.next = head;
        table.set(bucketIndex, newNode);
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

        HashTable<String, String> table = new HashTable<>(100);

        System.out.println(table.hashCode("string"));
        System.out.println(table.hashCode("string"));
        System.out.println(table.hashCode("aaaaaa"));
        System.out.println(table.hashCode("bbbbb"));

        System.out.println((int) 'a');
        System.out.println((int) 's');
    }
}
