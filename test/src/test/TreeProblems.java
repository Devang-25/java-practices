package test;

import java.util.ArrayList;
import java.util.LinkedList;

public class TreeProblems {
	String treeNode;

	TreeProblems() {
		treeNode = "";
	}

	void getInOrderTree(Node n) {
		if (n == null) {
			return;
		}
		getInOrderTree(n.left);
		// System.out.println(n.value);
		treeNode = treeNode + n.value + " ";
		// System.out.print(treeNode);
		getInOrderTree(n.right);
	}

	static class Node {
		int value;
		Node left;
		Node right;

		Node(int val) {
			value = val;
		}
	}

	ArrayList<LinkedList<TreeNode>> createLevelLinkedlist(TreeNode root) {

		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
		/* "Visit" the root */

		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

		if (root != null) {
			queue.add(root);

		}

		while (!queue.isEmpty()) {
			result.add(queue); // Add previous level
			LinkedList<TreeNode> parents = queue; // Go to next level
			queue = new LinkedList<TreeNode>();
			for (TreeNode parent : parents) {
				/* Visit the children */
				if (parent.left != null) {
					queue.add(parent.left);
				}
				if (parent.right != null) {
					queue.add(parent.right);
				}
			}
		}
		return result;
	}
	
	boolean isSUbTree(Node t1, Node t2){
		if(t1== null) {
			return false;
		}else if(t2==null) {
			return true;
		}else if(t1.value== t2.value &&  areTreesIdentical(t1,t2)){
			return true;
		}
		return isSUbTree(t1.left,t2) || isSUbTree(t1.right, t2);
	}
	
	boolean areTreesIdentical(Node r1, Node r2) {
		if(r1==null && r2==null) {
			return true;
		}
		if(r1==null || r2== null) {
			return false;
		}
		if(r1.value != r2.value) {
			return false;
		}
		return areTreesIdentical(r1.left, r2.left) && areTreesIdentical(r1.right, r2.right);
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeProblems t = new TreeProblems();
		TreeNode n = new TreeNode(2);
		TreeNode nl = new TreeNode(1);
		TreeNode nr = new TreeNode(3);
		n.left = nl;
		n.right = nr;
//		t.getInOrderTree(n);
//		System.out.println(t.treeNode);
//		
		ArrayList<LinkedList<TreeNode>> levels= t.createLevelLinkedlist(n);
		
		System.out.println(levels.get(1).getFirst().key);
		System.out.println(levels.get(1).getLast().key);
	}

}

class TreeNode{
	int key;
	TreeNode left;
	TreeNode right;
	TreeNode(int k){
		key=k;
	}
}
