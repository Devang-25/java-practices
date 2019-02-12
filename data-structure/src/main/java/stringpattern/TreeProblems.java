package stringpattern;

public class TreeProblems {
	
	int min= Integer.MIN_VALUE;
	int max= Integer.MAX_VALUE;
	/*
	 * 1) Given a tree, in which leaf nodes for a doubly linked cycle Print cycle.
	 * 			 a
	 * 			/ \
	 * 		   b    c
	 * 		  / \    \
	 *        d->e--->f
	 * 
	 */
	
	 class Node{
		char key;
		Node left;
		Node right;
		Node parent;
		Node next;
		Node(char key){
			this.key=key;
		}
	}
	
	 class BNode{
		int key;
		BNode left;
		BNode right; 
		BNode(int key){
			this.key=key;
		}
	 }
	 
	void printCycles(Node root, Node node) {
		
		if(node== null) return;
		System.out.println(node.key);
		if(node.left!=null) {
			
			printCycles(root, node.left);
		}
		if(node.left==null && node.right==null && node.next!=null) {
			printCycles(root, node.next);
		}
		
//		if(node.left==null && node.right==null && node.parent !=null) {
//			printCycles(root, node.parent);
//		}
	}
	
	
	/*
	 * 2) Tell me if binary tree is BST?
	 * 
	 */
	
	boolean isBinaryTreeBST(BNode root) {
		if(root==null) return true;
		return checkIsBST(root, min, max);
	}
	
	boolean checkIsBST(BNode node, int minVal, int maxVal) {
		
		if(node==null) {
			return true;
		} 
		if(node.key<=minVal || node.key>= maxVal) {
			return false;
		}
		return (checkIsBST(node.left, minVal, node.key) && checkIsBST(node.right, node.key, maxVal));

		
	}
	
	
	public static void main(String args[]) {
		TreeProblems t= new TreeProblems();
		Node node= t.new Node('a');
		node.left= t.new Node('b');
		node.left.parent= node;
		node.right= t.new Node('c');
		node.right.parent= node;
		node.left.left= t.new Node('d');
		node.left.right= t.new Node('e');
		node.left.left.parent= node.left;
		node.left.right.parent= node.left;
		node.left.left.next= node.left.right;
		node.right.right= t.new Node('f');
		node.right.right.parent= node.right;
		node.left.right.next= node.right.right;
		//t.printCycles(node, node);
		
		
		BNode n= t.new BNode(10);
		n.left= t.new BNode(8);
		n.right= t.new BNode(12);
		n.left.left= t.new BNode(6);
		n.left.right= t.new BNode(13);
		boolean isbst= t.isBinaryTreeBST(n);
		System.out.println(isbst);
	}

}
