package test;

import java.util.LinkedList;
import java.util.Queue;

import test.BFSDFSTreeTraversal.Node;

public class BFSDFSTreeTraversal {

	static class Node {
		int value;
		Node left;
		Node right;

		Node(int v) {
			value = v;
		}
	}

	static void preOrderTraversal(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.value + " ");
		preOrderTraversal(node.left);

		preOrderTraversal(node.right);

	}

	static void inOrderTraversal(Node node) {
		if (node == null) {
			return;
		}
		inOrderTraversal(node.left);

		System.out.print(node.value + " ");

		inOrderTraversal(node.right);

	}

	static void postOrderTraversal(Node node) {
		if (node == null) {
			return;
		}

		postOrderTraversal(node.left);

		postOrderTraversal(node.right);

		System.out.print(node.value + " ");
	}

	static void printPostorder(Node node) {
		if (node == null)
			return;

		// first recur on left subtree
		printPostorder(node.left);

		// then recur on right subtree
		printPostorder(node.right);

		// now deal with the node
		System.out.print(node.value + " ");
	}

	static void printLeaves(Node node) {

		if (node != null && node.left == null && node.right == null) {
			System.out.print(node.value + " ");
		}

		if (node != null && node.left != null) {
			printLeaves(node.left);
		}
		if (node != null && node.right != null) {
			printLeaves(node.right);
		}
	}
	
	static void bfsTraversal(Node n) {
		if(n==null) {
			return;
		}
		Queue<Node> q= new LinkedList<Node>();
		q.add(n);
		while(q.size()>0) {
			Node t= q.poll();
			System.out.print(t.value + " ");
			if(t.left !=null) {
				q.add(t.left);
			}
			if(t.right !=null) {
				q.add(t.right);
			}
		}
	}

	/*
	 * 1. example: BFS and DFSs of above Tree
	 * 
	 * Breadth First Traversal : 1 2 3 4 5
	 * 
	 * Depth First Traversals: Preorder Traversal : 1 2 4 5 3 Inorder Traversal : 4
	 * 2 5 1 3 Postorder Traversal : 4 5 2 3 1
	 * 
	 * 2. How to Pick One?
	 * 
	 * - Extra Space can be one factor (Explained above) - Depth First Traversals
	 * are typically recursive and recursive code requires function call overheads.
	 * - The most important points is, BFS starts visiting nodes from root while DFS
	 * starts visiting nodes from leaves. So if our problem is to search something
	 * that is more likely to closer to root, we would prefer BFS. And if the target
	 * node is close to a leaf, we would prefer DFS.
	 * 
	 * 3. Exercise: Which traversal should be used to print leaves of Binary Tree
	 * and why? Which traversal should be used to print nodes at k’th level where k
	 * is much less than total number of levels?
	 * 
	 * 
	 */
	
	//BFS

	/*
	 * For each node, first the node is visited and then it’s child nodes are put in
	 * a FIFO queue.
	 * 
	 * printLevelorder(tree) 1) Create an empty queue q 
	 * 2) temp_node = root // start from root 
	 * 3) Loop while temp_node is not NULL
	 *  a) print temp_node->data. 
	 *  b) Enqueue temp_node’s children (first left then right children) to q c) Dequeue
	 * a node from q and assign it’s value to temp_node
	 * 
	 */

	public static void main(String[] args) {
		Node n = new Node(1);
		n.left = new Node(2);
		n.right = new Node(3);
		n.left.left = new Node(4);
		n.left.right = new Node(5);
		n.right.left = new Node(6);
		n.right.right = new Node(7);

		// printLeaves(n);
		System.out.println("Pre order");
		preOrderTraversal(n);
		System.out.println("\nIn order");
		inOrderTraversal(n);
		System.out.println("\nPost order");
		postOrderTraversal(n);
		System.out.println("\nLevel order");
		bfsTraversal(n);
	}

}
