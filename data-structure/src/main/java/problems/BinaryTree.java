package problems;

import java.util.LinkedList;
import java.util.Queue;

import problems.BinaryTreeProblems.Node;

public class BinaryTree {
	int max_level = 0;

	class Node {
		int key;
		Node left;
		Node right;

		Node(int key) {
			this.key = key;
		}
	}

	void printLeftView(Node root) {
		printLeftView(root, 1);
	}

	void printLeftView(Node root, int level) {
		if (root == null)
			return;
		if (max_level < level) {
			System.out.println(root.key);
			max_level = level;
		}
		printLeftView(root.left, level + 1);
		printLeftView(root.right, level + 1);
	}

	void printRightView1(Node root) {
		printRightView1(root, 1);
	}

	void printRightView1(Node root, int level) {
		if (root == null)
			return;
		if (level > max_level) {
			
				System.out.println(root.key);
			
			max_level = level;
		}

		printRightView1(root.right, level + 1);
		printRightView1(root.left, level + 1);
	}

	void printRightView(Node root) {
		LinkedList<Node> parent = new LinkedList<Node>();
		parent.add(root);
		while (parent.size() > 0) {
			System.out.println(parent.get(parent.size() - 1).key);
			LinkedList<Node> current = new LinkedList<Node>();
			for (Node n : parent) {

				if (n.left != null)
					current.add(n.left);
				if (n.right != null)
					current.add(n.right);
			}
			parent = current;
		}

	}

	public static void main(String[] args) {
		BinaryTree bp = new BinaryTree();
		Node root = bp.new Node(10);
		root.left = bp.new Node(5);
		root.left.left = bp.new Node(4);
		root.right = bp.new Node(3);
		root.right.left = bp.new Node(7);
		root.left.right = bp.new Node(6);
		// bp.printLeftView(root);
		// bp.printRightView(root);

		bp.printRightView1(root);

	}

}
