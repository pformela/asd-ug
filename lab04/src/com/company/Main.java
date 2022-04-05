// dodac ostatni element listy i pozmieniac metody, żeby implementowały ostatni element listy

package com.company;

import java.util.HashMap;

class LinkedList<T> {

    private Node<T> head;

    static class Node<T> {
        T key;
        Node<T> next;
        Node<T> previous;

        Node(T d) {
            key = d;
            previous = null;
            next = null;
        }
    }

    public void insert(T data) {
        Node<T> newNode = new Node<>(data);

        if(this.head == null)
            this.head = newNode;
        else {
            Node<T> last = this.head;

            while(last.next != null)
                last = last.next;

            last.next = newNode;
            newNode.previous = last;
        }
    }

    public void insertAtTheBeginning(T data) {
        Node<T> newHead = new Node<>(data);
        newHead.next = this.head;
        newHead.previous = null;
        this.head = newHead;
    }

    public Node<T> find(T data) {

        Node<T> currentNode = this.head;

        while(currentNode != null) {
            if(currentNode.key == data) return currentNode;
            currentNode = currentNode.next;
        }

        return null;
    }

    public void delete(T data) {
        Node<T> currentNode = this.head;

        while(currentNode != null) {
            if(currentNode.key == data) {
                Node<T> previousNode = currentNode.previous;
                Node<T> nextNode = currentNode.next;
                previousNode.next = nextNode;
                nextNode.previous = previousNode;
                currentNode = null;
                break;
            }
            currentNode = currentNode.next;
        }
    }

    public LinkedList<T> uniqueValues() {

        if(this.head.next == null) return this;

        HashMap<T, T> uniqueValues = new HashMap<>();
        LinkedList<T> uniqueList = new LinkedList<>();

        Node<T> currentNode = this.head;

        while(currentNode != null) {
            Node<T> finalCurrentNode = currentNode;
            uniqueValues.computeIfAbsent(currentNode.key, k -> finalCurrentNode.key);
            currentNode = currentNode.next;
        }

        for(T key : uniqueValues.keySet()) {
            Node<T> newNode = new Node<>(key);

            if(uniqueList.head == null)
                uniqueList.head = newNode;
            else {
                Node<T> last = uniqueList.head;

                while(last.next != null)
                    last = last.next;

                last.next = newNode;
                newNode.previous = last;
            }
        }

        return uniqueList;
    }

    public LinkedList<T> merge(LinkedList<T> secondList) {
        LinkedList<T> newList = new LinkedList<>();

        newList.head = this.head;

        Node<T> lastNode = newList.head;

        while(lastNode != null) {
            if(lastNode.next == null) break;
            lastNode = lastNode.next;
        }

        lastNode.next = secondList.head;
        secondList.head.previous = lastNode;

        return newList;
    }

    public void printList() {
        Node<T> currentNode = this.head;

        while(currentNode != null) {
            System.out.print(currentNode.key);
            if(currentNode.next != null)
                System.out.print(" <-> ");
            else
                System.out.println(" <-> null");

            currentNode = currentNode.next;
        }
    }
}


public class Main {

    public static void main(String[] args) {
//        LinkedList<Integer> myLinkedList = new LinkedList<>();
//        myLinkedList.insert(1);
//        myLinkedList.insert(1);
//        myLinkedList.insert(1);
//        myLinkedList.insert(2);
//        myLinkedList.insert(3);
//        myLinkedList.insert(4);
//        myLinkedList.insert(1);
//        myLinkedList.insert(1);
//        myLinkedList.insert(1);
//        myLinkedList.insert(5);
//
//        myLinkedList.printList();
//
//        LinkedList<Integer> newLinkedList = myLinkedList.uniqueValues();
//
//        newLinkedList.printList();
//        myLinkedList.insertAtTheBeginning(7);
//        myLinkedList.insertAtTheBeginning(6);
//
//        myLinkedList.printList();
//
//        LinkedList.Node<Integer> myNode = myLinkedList.find(3);
//        System.out.println(myNode);
//
//        myLinkedList.delete(4);
//
//        myLinkedList.printList();

        LinkedList<String> myList = new LinkedList<>();
        myList.insert("ala");
        myList.insert("ma");
        myList.insert("kota");
        myList.insert("ma");
        myList.insert("kota");
        myList.insert("ala");

        LinkedList<String> myListUnique = myList.uniqueValues();

        System.out.println(">>> myList: ");
        myList.printList();
        System.out.println(">>> myListUnique: ");
        myListUnique.printList();

        LinkedList<String> myList2 = new LinkedList<>();
        myList2.insert("kamil");
        myList2.insert("ślimak");
        myList2.insert("ślimak");

        LinkedList<String> myList2Unique = myList2.uniqueValues();

        System.out.println(">>> myList2: ");
        myList2.printList();
        System.out.println(">>> myList2Unique: ");
        myList2Unique.printList();

        LinkedList<String> mergedList = myList.merge(myList2);

        System.out.println(">>> Merged list: ");
        mergedList.printList();

    }
}
