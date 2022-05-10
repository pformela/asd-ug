package com.company;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BST {

    public static class Node {
        Node left;
        Node right;
        int value;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static class BinarySearchTree {
        Node root;

        public BinarySearchTree() {
            this.root = null;
        }

        public void insert(int value) {

            Node newNode = new Node(value);
            Node currNode = this.root;

            if(this.root == null) {
                this.root = newNode;
                return;
            }

            while (true) {
                if(newNode.value >= currNode.value)
                    if(currNode.right != null) {
                        currNode = currNode.right;
                    } else {
                        currNode.right = newNode;
                        break;
                    }
                else {
                    if(currNode.left != null) {
                        currNode = currNode.left;
                    } else {
                        currNode.left = newNode;
                        break;
                    }
                }
            }

        }


        public Node lookup(int value) {

            Node currNode = this.root;

            while (currNode != null) {
                if(currNode.value == value)
                    return currNode;
                else if(value > currNode.value)
                    currNode = currNode.right;
                else
                    currNode = currNode.left;
            }

            return null;
        }

        public static String centerString (int width, String s) {
            return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
        }

        public void printFragment() {

            ArrayList<Integer> bstToList = new ArrayList<>();
//            Queue<Integer> nodeQueue = new PriorityQueue<>();
//
//            for(int i = 0; i < 4; i++) {
//
//            }

            bstToList.add(this.root.value);
            bstToList.add(this.root.left.value);
            bstToList.add(this.root.right.value);

            System.out.println(centerString(32, Integer.toString(this.root.value)));
            System.out.print(centerString(16, Integer.toString(this.root.right.value)));
            System.out.print(centerString(16, Integer.toString(this.root.left.value)));
            System.out.println();
        }

    }

    public static void main(String[] args) {
        BinarySearchTree myBst = new BinarySearchTree();

        myBst.insert(9);
        myBst.insert(4);
        myBst.insert(6);
        myBst.insert(20);
        myBst.insert(170);
        myBst.insert(15);
        myBst.insert(1);

        System.out.println(myBst.root);

        System.out.println(myBst.lookup(20));
        System.out.println(myBst.lookup(21));
        System.out.println(myBst.lookup(1));

        myBst.printFragment();

    }

}

