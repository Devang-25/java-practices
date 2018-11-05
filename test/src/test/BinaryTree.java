package test;

import java.util.LinkedList;

public class BinaryTree {
	
	// 'root' - root of binary tree
    Node root;
     
    // 'head' - reference to head node of created
    //double linked list
    Node head;

	static class Node {
		int value;
		Node left;
		Node right;

		Node(int key) {
			value = key;
		}
	}

	
	// Binary tree to Doubly Linked List
    void BToDLL(Node root) 
    {
        // Base cases
        if (root == null)
            return;
 
        // Recursively convert right subtree
        BToDLL(root.right);
 
        // insert root into DLL
        root.right = head;
 
        // Change left pointer of previous head
        if (head != null)
            (head).left = root;
 
        // Change head of Doubly linked list
        head = root;
 
        // Recursively convert left subtree
        BToDLL(root.left);
    }

	// Find max depth of a tree
	static int findMaxDepth(Node node) {
		if (node == null) {
			return 0;
		}
		int l = findMaxDepth(node.left);
		int r = findMaxDepth(node.right);
		return Math.max(l, r) + 1;
	}

	// convert a binary tree into doubly linked list

	 void binTreeToLL(Node root) {
		if(root==null) {
			return;
		}
		binTreeToLL(root.right);
		if(head== null) {
			Node left= root.left;
			head=root;
			root= head.left;
		}
		Node t= head;
		head=root;
		head.right=t;
		binTreeToLL(root.left);
	}
	 void printList(Node head) 
	    {
	        System.out.println("Extracted Double Linked List is : ");
	        while (head != null) 
	        {
	            System.out.print(head.value + " ");
	            head = head.right;
	        }
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node n = new Node(5);
		n.left = new Node(4);
		n.left.left = new Node(3);
		n.right = new Node(7);
		n.right.left = new Node(6);
		// System.out.println(findMaxDepth(n));


		BinaryTree b= new BinaryTree();
				b.binTreeToLL(n);
				b.printList(b.head);




	}

}
