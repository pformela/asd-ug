package com.company;

import java.util.HashMap;

class LinkedList<T> {

    private final Node<T> none = new Node<>(null); //wartownik
    private Node<T> head;
    private Node<T> tail;

    static class Node<T> {
        T key;
        Node<T> next;
        Node<T> previous;

        Node(T data) {
            key = data;
            previous = null;
            next = null;
        }
    }

    public void dodaj(T data) {
        Node<T> newNode = new Node<>(data);

        if(this.head == null) {
            this.head = newNode;
            this.head.previous = this.none;
            this.none.next = this.head;
        }
        else {
            this.tail.next = newNode;
            newNode.previous = this.tail;
        }

        this.tail = newNode;
        this.tail.next = this.none;
        this.none.previous = this.tail;
    }

    public void wstaw(T data) {
        Node<T> newHead = new Node<>(data);
        newHead.next = this.head;
        newHead.previous = this.none;
        this.head = newHead;
        this.none.next = this.head;
    }

    public Node<T> szukaj(T data) {

        Node<T> currentNode = this.head;

        while(currentNode != this.none) {
            if(currentNode.key == data) return currentNode;
            currentNode = currentNode.next;
        }

        return null;
    }

    public void usun(T data) {
        Node<T> currentNode = this.head;

        if(currentNode.key == data) {
            this.head = this.head.next;
            this.head.previous = this.none;
            this.none.next = this.head;
            return;
        }

        while(currentNode != this.none) {
            if(currentNode.key == data && currentNode.next == this.none) {
                this.tail = this.tail.previous;
                this.tail.next = this.none;
                this.none.previous = this.tail;
                break;
            } else if(currentNode.key == data) {
                Node<T> previousNode = currentNode.previous;
                Node<T> nextNode = currentNode.next;
                previousNode.next = nextNode;
                nextNode.previous = previousNode;
                break;
            }
            currentNode = currentNode.next;
        }
    }

    public LinkedList<T> bezPowtorzen() {

        if(this.head.next == null) return this;

        HashMap<T, T> uniqueValues = new HashMap<>();
        LinkedList<T> uniqueList = new LinkedList<>();
        uniqueList.none.key = null;

        Node<T> currentNode = this.head;

        while(currentNode != this.none) {
            Node<T> finalCurrentNode = currentNode;
            uniqueValues.computeIfAbsent(currentNode.key, k -> finalCurrentNode.key);
            currentNode = currentNode.next;
        }

        Node<T> lastNode = uniqueList.none;

        for(T key : uniqueValues.keySet()) {
            Node<T> newNode = new Node<>(key);

            if(uniqueList.head == null) {
                uniqueList.head = newNode;
                uniqueList.none.next = uniqueList.head;
                uniqueList.none.previous = uniqueList.head;
                uniqueList.head.previous = uniqueList.none;
                lastNode = uniqueList.head;
            }
            else {
                lastNode.next = newNode;
                newNode.previous = lastNode;
                newNode.next = uniqueList.none;
                uniqueList.none.previous = newNode;
                lastNode = newNode;
            }
        }

        uniqueList.tail = lastNode;

        return uniqueList;
    }


    public LinkedList<T> scal(LinkedList<T> secondList) {
        LinkedList<T> newList = new LinkedList<>();

        newList.head = this.head;
        newList.none.next = this.head;
        this.head.previous = newList.none;

        Node<T> lastNode = this.tail;

        lastNode.next = secondList.head;
        secondList.head.previous = lastNode;
        newList.tail = secondList.tail;
        newList.tail.next = newList.none;
        newList.none.previous = newList.tail;

        return newList;
    }

    public void drukuj() {
        Node<T> currentNode = this.head;

        System.out.print("none <-> ");

        while(currentNode != this.none) {
            System.out.print(currentNode.key);
            if(currentNode.next != this.none)
                System.out.print(" <-> ");
            else if(currentNode == this.tail)
                System.out.println(" <-> none");

            currentNode = currentNode.next;
        }
    }
}


public class Main {

    public static void main(String[] args) {

        LinkedList<Integer> myLinkedList = new LinkedList<>();
        myLinkedList.dodaj(1);
        myLinkedList.dodaj(1);
        myLinkedList.dodaj(1);
        myLinkedList.dodaj(2);
        myLinkedList.dodaj(3);
        myLinkedList.dodaj(3);
        myLinkedList.dodaj(3);
        myLinkedList.dodaj(4);
        myLinkedList.dodaj(5);

        System.out.println("Integers >>> LinkedList with repetitions: ");
        myLinkedList.drukuj();

        System.out.println("Integers >>> LinkedList without repetitions: ");
        myLinkedList = myLinkedList.bezPowtorzen();
        myLinkedList.drukuj();

        System.out.println("Integers >>> LinkedList without 1: ");
        myLinkedList.usun(1);
        myLinkedList.drukuj();

        System.out.println("Integers >>> LinkedList without 5: ");
        myLinkedList.usun(5);
        myLinkedList.drukuj();

        System.out.println("Integers >>> LinkedList without 3: ");
        myLinkedList.usun(3);
        myLinkedList.drukuj();

        System.out.println("Integers >>> LinkedList with inserted 99: ");
        myLinkedList.dodaj(99);
        myLinkedList.drukuj();

        System.out.println("Integers >>> LinkedList with inserted 77 at the beggining: ");
        myLinkedList.wstaw(77);
        myLinkedList.drukuj();

        System.out.println("Integers >>> Find 4 in LinkedList (if true returns first Node with this key else returns null): ");
        System.out.println(myLinkedList.szukaj(4));

        System.out.println("Integers >>> Find 66 in LinkedList (if true returns first Node with this key else returns null): ");
        System.out.println(myLinkedList.szukaj(66));

        LinkedList<Integer> myLinkedList2 = new LinkedList<>();
        myLinkedList2.dodaj(11);
        myLinkedList2.dodaj(12);
        myLinkedList2.dodaj(13);

        System.out.println("Integers >>> Second LinkedList: ");
        myLinkedList2.drukuj();

        LinkedList<Integer> mergedLinkedList = myLinkedList.scal(myLinkedList2);

        System.out.println("Integers >>> LinkedList merged with Second LinkedList: ");
        mergedLinkedList.drukuj();

        // -------------------------------------------------------------------------------------------------------

        LinkedList<String> myLinkedListStrings = new LinkedList<>();
        myLinkedListStrings.dodaj("a");
        myLinkedListStrings.dodaj("a");
        myLinkedListStrings.dodaj("a");
        myLinkedListStrings.dodaj("b");
        myLinkedListStrings.dodaj("c");
        myLinkedListStrings.dodaj("c");
        myLinkedListStrings.dodaj("c");
        myLinkedListStrings.dodaj("d");
        myLinkedListStrings.dodaj("e");

        System.out.println("\n\nStrings >>>> LinkedList with repetitions: ");
        myLinkedListStrings.drukuj();

        System.out.println("Strings >>>> LinkedList without repetitions: ");
        myLinkedListStrings = myLinkedListStrings.bezPowtorzen();
        myLinkedListStrings.drukuj();

        System.out.println("Strings >>>> LinkedList without a: ");
        myLinkedListStrings.usun("a");
        myLinkedListStrings.drukuj();

        System.out.println("Strings >>>> LinkedList without e: ");
        myLinkedListStrings.usun("e");
        myLinkedListStrings.drukuj();

        System.out.println("Strings >>>> LinkedList without c: ");
        myLinkedListStrings.usun("c");
        myLinkedListStrings.drukuj();

        System.out.println("Strings >>>> LinkedList with inserted xx: ");
        myLinkedListStrings.dodaj("xx");
        myLinkedListStrings.drukuj();

        System.out.println("Strings >>>> LinkedList with inserted zz at the beggining: ");
        myLinkedListStrings.wstaw("zz");
        myLinkedListStrings.drukuj();

        System.out.println("Strings >>>> Find d in LinkedList (if true returns first Node with this key else returns null): ");
        System.out.println(myLinkedListStrings.szukaj("d"));

        System.out.println("Strings >>>> Find g in LinkedList (if true returns first Node with this key else returns null): ");
        System.out.println(myLinkedListStrings.szukaj("g"));

        LinkedList<String> myLinkedListStrings2 = new LinkedList<>();
        myLinkedListStrings2.dodaj("mm");
        myLinkedListStrings2.dodaj("nn");
        myLinkedListStrings2.dodaj("oo");

        System.out.println("Strings >>>> Second LinkedList: ");
        myLinkedListStrings2.drukuj();

        LinkedList<String> mergedLinkedListString = myLinkedListStrings.scal(myLinkedListStrings2);

        System.out.println("Strings >>>> LinkedList merged with Second LinkedList: ");
        mergedLinkedListString.drukuj();

    }
}
