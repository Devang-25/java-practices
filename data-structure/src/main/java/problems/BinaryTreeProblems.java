package problems;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeProblems {
	static boolean v1 = false, v2 = false; 

	class Node {
		int key;
		Node left;
		Node right;

		Node(int key) {
			this.key = key;
		}
	}

	Node findLowestCommonAncestor(Node root, int n1, int n2) {

		Node l1 = findParent(root, n1);
		Node r1 = findParent(root, n2);
		System.out.println(("l1: " + l1.key + ", r1: " + r1.key));
		while (l1 != null && r1 != null) {
			if (l1.key == r1.key)
				break;
			l1 = findParent(root.left, n1);
			r1 = findParent(root.left, n2);

		}
		if (l1 != null && r1 != null && l1.key == r1.key)
			return l1;

		while (l1 != null && r1 != null) {
			if (l1.key == r1.key)
				break;
			l1 = findParent(root.right, n1);
			r1 = findParent(root.right, n2);

		}
		if (l1 != null && r1 != null) {
			if (l1.key == r1.key) {
				return l1;
			} else
				return root;
		} else {
			return null;
		}
	}

	Node findParent(Node root, int n) {
		if (root == null || root.key == n)
			return null;
		if ((root.left != null && root.left.key == n) || (root.right != null && root.right.key == n)) {
			return root;
		}
		Node l = findParent(root.left, n);
		Node r = findParent(root.right, n);
		return l == null ? r : l;
	}

	Integer findLCA(Node root, int n1, int n2) {
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		if(!findPath(root, n1, list1) || !findPath(root, n2, list2)) {
			if(list1.size()>0)System.out.println("n1 is present, n2 is missing");
			else System.out.println("n2 is present, n1 is missing");
			return -1;
		}
		int l1 = 0;
		for (; l1 < list1.size() - 1 && l1 < list2.size(); l1++) {
			if (list1.get(l1) != list2.get(l1)) {

				break;
			}
		}
		return list1.get(l1 - 1);
	}

	boolean findPath(Node root, int n, List<Integer> list) {

		if (root == null)
			return false;
		list.add(root.key);
		// for(Integer l: list) {
		// System.out.print(l+ " ");
		// }
		// System.out.println();
		if (root.key == n) {
			return true;
		}
		if (root.left != null && findPath(root.left, n, list)) {
			return true;
		}
		if (root.right != null && findPath(root.right, n, list)) {
			return true;
		}
		list.remove(list.size() - 1);
		return false;
	}

	
	Node findLCANodeIfExist(Node root, int n1, int n2) {
		if(root==null) return null;
		if(root.key== n1 || root.key==n2 ) return root;
		Node lcaLeft= findLCANodeIfExist(root.left, n1,n2);
		Node lcaRight= findLCANodeIfExist(root.right, n1,n2);
		
		if(lcaLeft!=null && lcaRight!=null) return root;
		if(lcaLeft!=null) return lcaLeft;
		else return lcaRight;
	}
	
	Node findLCAUtil(Node node, int n1, int n2) 
    { 
        // Base case 
        if (node == null) 
            return null; 
          
        //Store result in temp, in case of key match so that we can search for other key also. 
        Node temp=null; 
  
        
        if (node.key == n1) 
        { 
            v1 = true; 
            temp = node; 
        } 
        if (node.key == n2) 
        { 
            v2 = true; 
            temp = node; 
        } 
        
        
  
        // Look for keys in left and right subtrees 
        Node left_lca = findLCAUtil(node.left, n1, n2); 
        Node right_lca = findLCAUtil(node.right, n1, n2); 
  
        if (temp != null) 
            return temp; 
  
        if (left_lca != null && right_lca != null) 
            return node; 
  
        return (left_lca != null) ? left_lca : right_lca; 
    } 
  
    // Finds lca of n1 and n2 under the subtree rooted with 'node' 
    Node findLCAModified(Node root, int n1, int n2) 
    { 
        // Initialize n1 and n2 as not visited 
        v1 = false; 
        v2 = false; 
  
        // Find lca of n1 and n2 using the technique discussed above 
        Node lca = findLCAUtil(root, n1, n2); 
  
        // Return LCA only if both n1 and n2 are present in tree 
        if (v1 && v2) 
            return lca; 
  
        // Else return NULL 
        return null; 
    } 
	
	
	public static void main(String[] args) {
		BinaryTreeProblems bp = new BinaryTreeProblems();
		Node root = bp.new Node(10);
		root.left = bp.new Node(5);
		root.left.left = bp.new Node(4);
		root.right = bp.new Node(3);
		root.right.left = bp.new Node(7);
		root.right.right = bp.new Node(6);
		Node ances = bp.findParent(root, 7);
		System.out.println(ances != null ? ances.key : null);
		List<Integer> list = new ArrayList<Integer>();
		bp.findPath(root, 7, list);
		for (Integer l : list) {
			System.out.print(l + " ");
		}
		System.out.println();
		List<Integer> list1 = new ArrayList<Integer>();
		bp.findPath(root, 6, list1);
		for (Integer l : list1) {
			System.out.print(l + " ");
		}
		System.out.println();
		Integer value = bp.findLCA(root, 11, 6);
		System.out.print(value);
		
		System.out.println();
		Node  n = bp.findLCANodeIfExist(root, 7, 4);
		System.out.print(n.key);
		
		System.out.println();
		Node  n1 = bp.findLCAModified(root, 7,6);
		System.out.print(n1 !=null? n1.key: null);
		
		
	}

}
