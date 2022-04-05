package com.company;

import com.sun.security.jgss.GSSUtil;

class LinkedList<T> {

    private Node head;

    static class Node<T> {
        T key;
        Node next;

        Node(T d) {
            key = d;
            next = null;
        }
    }

    public void insert(T data) {
        Node newNode = new Node<>(data);
        newNode.next = null;

        if(this.head == null)
            this.head = newNode;
        else {
            Node last = this.head;

            while(last.next != null)
                last = last.next;

            last.next = newNode;
        }
    }

    public void insertAtTheBeggining(T data) {
        Node newHead = new Node<>(data);
        newHead.next = this.head;
        this.head = newHead;
    }

    public Node find(T data) {

        Node currentNode = this.head;

        while(currentNode.next == null) {
            if(currentNode.key == data) return currentNode;
            currentNode = currentNode.next;
        }

        return null;
    }

    public void printList() {
        Node currentNode = this.head;

        while(currentNode != null) {
            System.out.print(currentNode.key);
            if(currentNode.next != null)
                System.out.print(" -> ");
            else
                System.out.println(" -> null");

            currentNode = currentNode.next;
        }
    }
}


public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> myLinkedList = new LinkedList<>();
        myLinkedList.insert(1);
        myLinkedList.insert(2);
        myLinkedList.insert(3);
        myLinkedList.insert(4);
        myLinkedList.insert(5);

        myLinkedList.printList();

        myLinkedList.insertAtTheBeggining(7);
        myLinkedList.insertAtTheBeggining(6);

        myLinkedList.printList();

        LinkedList.Node<Integer> myNode = myLinkedList.find(3);
        System.out.println(myNode);
    }
}
