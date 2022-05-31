package com.company;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
=======
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
>>>>>>> 8712d36555571126d11d40f33eefb0ab07902a1b

public class BST {

    public static class Node {
        Node left;
        Node right;
<<<<<<< HEAD
        int value;

        Node(int value) {
=======
        String value;

        Node(String value) {
>>>>>>> 8712d36555571126d11d40f33eefb0ab07902a1b
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

<<<<<<< HEAD
        public void insert(int value) {
=======
        public void insert(String value) {
>>>>>>> 8712d36555571126d11d40f33eefb0ab07902a1b

            Node newNode = new Node(value);
            Node currNode = this.root;

            if(this.root == null) {
                this.root = newNode;
                return;
            }

            while (true) {
<<<<<<< HEAD
                if(newNode.value >= currNode.value)
=======
                if((newNode.value).compareTo(currNode.value) >= 0)
>>>>>>> 8712d36555571126d11d40f33eefb0ab07902a1b
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


<<<<<<< HEAD
        public Node lookup(int value) {
=======
        public Node lookup(String value) {
>>>>>>> 8712d36555571126d11d40f33eefb0ab07902a1b

            Node currNode = this.root;

            while (currNode != null) {
<<<<<<< HEAD
                if(currNode.value == value)
                    return currNode;
                else if(value > currNode.value)
=======
                if(currNode.value.equals(value))
                    return currNode;
                else if(value.compareTo(currNode.value) >= 0)
>>>>>>> 8712d36555571126d11d40f33eefb0ab07902a1b
                    currNode = currNode.right;
                else
                    currNode = currNode.left;
            }

            return null;
        }

        public static String centerString (int width, String s) {
<<<<<<< HEAD
=======
            if (s == null)
                s = " ";
>>>>>>> 8712d36555571126d11d40f33eefb0ab07902a1b
            return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
        }

        public void printFragment() {

<<<<<<< HEAD
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
=======
            System.out.println(centerString(128, (this.root != null) ? this.root.value : " "));
            System.out.println();
            System.out.print(centerString(64, (this.root.left != null) ? this.root.left.value : " "));
            System.out.println(centerString(64, (this.root.right != null) ? this.root.right.value : " " ));
            System.out.println();
            System.out.print(centerString(32, (this.root.left.left != null) ? this.root.left.left.value  : " "));
            System.out.print(centerString(32, (this.root.left.right != null) ? this.root.left.right.value : " "));
            System.out.print(centerString(32, (this.root.right.left != null) ? this.root.right.left.value : " "));
            System.out.println(centerString(32, (this.root.right.right != null) ? this.root.right.right.value : " "));
            System.out.println();
            System.out.print(centerString(16, (this.root.left.left.left != null) ? this.root.left.left.left.value : " "));
            System.out.print(centerString(16, (this.root.left.left.right != null) ? this.root.left.left.right.value : " "));
            System.out.print(centerString(16, (this.root.left.right.left != null) ? this.root.left.right.left.value : " "));
            System.out.print(centerString(16, (this.root.left.right.right != null) ? this.root.left.right.right.value  : " "));
            System.out.print(centerString(16, (this.root.right.left.left != null) ? this.root.right.left.left.value : " "));
            System.out.print(centerString(16, (this.root.right.left.right != null) ? this.root.right.left.right.value : " "));
            System.out.print(centerString(16, (this.root.right.right.left != null) ? this.root.right.right.left.value  : " "));
            System.out.println(centerString(16, (this.root.right.right.right != null) ? this.root.right.right.right.value : " "));

        }

        public int binaryTreeHeight(Node root) {
            if(root == null)
                return 0;
            int leftHeight = binaryTreeHeight(root.left);
            int rightHeight = binaryTreeHeight(root.right);

            return Math.max(leftHeight, rightHeight) + 1;
>>>>>>> 8712d36555571126d11d40f33eefb0ab07902a1b
        }

    }

    public static void main(String[] args) {
        BinarySearchTree myBst = new BinarySearchTree();

<<<<<<< HEAD
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
=======
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int numberOfWords = 2000;

        try {
            while (numberOfWords > 0) {
                String currLine = bufferedReader.readLine();
                myBst.insert(currLine.toLowerCase());
                numberOfWords--;
            }

            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Caught exception: ");
            e.printStackTrace();
        }

        System.out.println("Binary tree height: " + myBst.binaryTreeHeight(myBst.root));
>>>>>>> 8712d36555571126d11d40f33eefb0ab07902a1b

        myBst.printFragment();

    }
<<<<<<< HEAD

}

=======
}

// 500 words
// Binary tree height: 19

// 1000 words
// Binary tree height: 23

// 2000 words
// Binary tree height: 28
>>>>>>> 8712d36555571126d11d40f33eefb0ab07902a1b
