package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import problems.BinaryTree.Node;

public class AmazonProblems {
	
	int maxlevel=0;
	
	/*
	   *** important and done
	   ** done
	 	1) K largest elements from a big file or array.
	 	
	 	2)Find a triplet a, b, c such that a2 = b2 + c2. Variations of this problem like find a triplet with sum equal to 0. Find a pair with given sum. All such questions are efficiently solved using hashing. – Practice here
			
		3) Binary tree traversal questions like left view, right view, top view, bottom view, 
			maximum of a level, minimum of a level, children sum property, diameter etc.
		
		4) Convert a Binary tree to DLL – Practice here
		
		5)*** Lowest Common ancestor in a Binary Search Tree and Binary Tree.
		
		7) ** Implement a stack with push(), pop() and min() in O(1) time.
		
		8) *** Reverse a linked list in groups of size k – Practice here
		
		9) Given two numbers represented by two linked lists, write a function that returns sum list – Practice here
		
		10) Rotate a matrix by 90 degree.
		
		11) Stock span problem
		
		12) Next greater element
		
		12) Some Dynamic Programming problems like:
		
		Maximum sum subarray such that no elements are consecutive – Practice here
		Edit distance
		Assembly line scheduling
	 */
	// 1) K largest elements from a big file or array.
	void heapify(int[] arr, int n, int i) {
		int max= i;
		int left=2*i+1;
		int right=2*i+2;
		if(left<n && arr[left]>arr[i]) {
			max= left;
		}
		if(right<n && arr[right]>arr[max]) {
			max= right;
		}
		if(max!=i) {
			swap(arr, i,max);
			heapify(arr, n, max);
		}
	}
	
	void swap(int[] arr, int i, int j) {
		int t= arr[i];
		arr[i]=arr[j];
		arr[j]= t;
	}
	
	void buildMaxHeap(int[] arr, int n) {
		for(int i=n/2; i>=0; i--) {
			heapify(arr,n, i);
		}
	}
	
	int[] getKLargest(int[] arr, int k) {
		int n= arr.length;
		if(n<k) return null;
	//	if(n==k) return arr;
		
		buildMaxHeap(arr, n);
		int[] largest= new int[k];
		
		for(int i=0; i<k; i++) {
			largest[i]= arr[0];
			swap(arr, 0, n-1-i);
			heapify(arr,n-1-i, 0);
		}
		return largest;
	}
	
	//2) Find a triplet a, b, c such that a2 = b2 + c2. Variations of this problem like find a triplet with sum equal to 0. Find a pair with given sum. All such questions are efficiently solved using hashing. – Practice here
	
	void printTriplet(int[] arr) {
		Set<Integer> set= new HashSet<Integer>();
		int n=arr.length;
		for(int i=0; i<n-1; i++) {
			for(int j=i+1; j<n; j++) {
				int find1= arr[i]* arr[i];
				int find2=  arr[j]*arr[j];
				int find3= find1 + find2;
				if(!set.contains(find3)) {
					set.add(find1);
					set.add(find2);
					set.add(find3);
					
				} else {
					System.out.println(arr[i] + " "+ arr[j] + " " + Math.sqrt(find3));
				}
			}
			
		}
	}
	
	// 3) Binary tree traversal questions like left view, right view, top view, bottom view, maximum of a level, minimum of a level, children sum property, diameter etc.
	
	
	class QNode{
		Node node;
		int hd;
		QNode(Node node, int hd){
			this.node=node;
			this.hd=hd;
		}
	}
	public void printTopView(Node root) {
		Queue<QNode> parent= new LinkedList<QNode> ();
		Map<Integer, Integer> map= new HashMap<Integer, Integer> ();
		parent.add(new QNode(root,0));
		
		while(parent.size()>0) {
			QNode t= parent.poll();
			
			if(map.get(t.hd)==null) {
				map.put(t.hd, t.node.key);
			}
			
			if(t.node.left!=null) parent.add(new QNode(t.node.left, t.hd -1));
			if(t.node.right!=null) parent.add(new QNode(t.node.right, t.hd +1));
			
		}
			
		for(int i=(map.size()-1)*-1; i<=0; i++) {
			if(map.get(i)!=null) {
				System.out.print(map.get(i)+" ");
			}
		}
		for(int i=1; i<map.size(); i++) {
			if(map.get(i)!=null) {
				System.out.print(map.get(i)+" ");
			}
		}
	}
	
	
	public void printBottomView(Node root) {
		Queue<QNode> parent= new LinkedList<QNode> ();
		Map<Integer, Integer> map= new HashMap<Integer, Integer> ();
		parent.add(new QNode(root,0));
		
		while(parent.size()>0) {
			QNode t= parent.poll();
			
			
			map.put(t.hd, t.node.key);
			
			
			if(t.node.left!=null) parent.add(new QNode(t.node.left, t.hd -1));
			if(t.node.right!=null) parent.add(new QNode(t.node.right, t.hd +1));
			
		}
			
		for(int i=(map.size()-1)*-1; i<=0; i++) {
			if(map.get(i)!=null) {
				System.out.print(map.get(i)+" ");
			}
		}
		for(int i=1; i<map.size(); i++) {
			if(map.get(i)!=null) {
				System.out.print(map.get(i)+" ");
			}
		}
	}
	
	public void getLeftView(Node root, int level, List<Integer> list) {
		if(root==null) return;
		
		if(level>maxlevel) {
			list.add(root.key);
			maxlevel=level;
		}
		getLeftView(root.left, level+1, list);
		getLeftView(root.right, level+1, list);
	}
	
	public void getRightView(Node root, int level, List<Integer> list) {
		if(root==null) return;
		
		if(level>maxlevel) {
			list.add(root.key);
			maxlevel=level;
		}
		getLeftView(root.right, level+1, list);
		getLeftView(root.left, level+1, list);
	}
	
	public int getMaxLevelSum(Node root) {
		Queue<Node> q= new LinkedList<Node> ();
		q.add(root);
		int maxSum=0;
		while(q.size()!=0) {
			int count=q.size();
			int sum=0;
			
			while(count>0) {
				Node t= q.poll();
				sum+= t.key;
				count--;
				if(t.left!=null) q.add(t.left);
				if(t.right!=null) q.add(t.right);
				
			}
			System.out.println(sum+": "+ maxSum);
			if(maxSum<sum) maxSum=sum;
			
		}
		return maxSum;
	}
	
	//4) Convert a Binary tree to DLL
	
	class DLLNode{
		int key;
		DLLNode prev;
		DLLNode next;
		
		DLLNode(int key){
			this.key= key;
		}
	}
	

	
	DLLNode prev=null;
	DLLNode head=null;
	
	void convertBinTreeToDLL(Node root) {
		
		if(root==null) return ;
		 convertBinTreeToDLL(root.left);
		 
		if(prev==null) {
			prev= new DLLNode(root.key);
			head=prev;
		} else {
			DLLNode n= new DLLNode(root.key);
			prev.next= n;
			n.prev= prev;
			prev=n;
		}
		//System.out.println(prev.key);
		convertBinTreeToDLL(root.right);
		
		
	}
	


	public static void main(String[] args) {
		AmazonProblems ap= new AmazonProblems();
		// 1)
		int[] arr= {2,4,8,10,9,1,5,3,7};
		
//		int[] res= ap.getKLargest(arr,8);
//		for(int i=0; i<8; i++) {
//			System.out.print(res[i]+" ");
//		}
//		System.out.println();
//		System.out.println(res.length);
		
		//2)

		int a[]= {2,3,4,1,5,7};
	//	ap.printTriplet(a);
		
		//3
		BinaryTree bt= new BinaryTree();
		Node root= bt.new Node(10);
		root.left= bt.new Node(9);
		root.left.left= bt.new Node(6);
		root.left.right= bt.new Node(7);
		root.left.right.right= bt.new Node(5);
		root.right= bt.new Node(12);
		root.right.left= bt.new Node(11);
		root.right.right= bt.new Node(14);
		
		/**
		 		   10
		 		   /\
		 		  9   12
		 		 / \  /  \
		 		 6 7,11  14
		 		    \
		 		     5
		 */
		
//		ap.printTopView(root);
//		System.out.println();
//		ap.printBottomView(root);
		
//		int max= ap.getMaxLevelSum(root);
//		System.out.println();
//		System.out.println(max);
		
		ap.convertBinTreeToDLL(root);
		
		System.out.println(ap.head);
		while(ap.head!=null) {
			System.out.println(ap.head.key);
			ap.head=ap.head.next;
		}
	}

}
