package stringpattern;

public class BinaryTreeProblem {
	
	/*
	 	1. Deletion in a binary search tree
		
		3. Couple of more questions on trees.
	 */
	
	
	Node deleteInBSTree(Node root, int value) {
		if(root== null) return root;
		//System.out.println(root.key);
		if(value< root.key ) {
			root.left=deleteInBSTree(root.left, value);
				
		} else if(value> root.key) {
			root.right=deleteInBSTree(root.right, value);
		}
		else {
			if(root.right==null) {
				root= root.left;
			} else if(root.left==null) {
				root= root.right;
			} else {
				root.key= minValue(root.right);
				root.right= deleteInBSTree(root.right, root.key);
			}
				
		}
		return root;
	}
	
	int minValue(Node root) {
		int minval= root.key;
		while(root.left!=null) {
			minval= root.left.key;
			root= root.left;
		}
		return minval;
	}
	
	
	// 2. Construct a BST with postorder , preorder and inorder traversals given
	Node buildBSTFromPostorder(int[] postorder, int l, int h) {
		if(l>h) return null;
		Node root= new Node(postorder[h]);
		if(l==h) return root;
		
		int sp= findSeparationPoint(postorder, l, h-1, postorder[h]);
		root.left=buildBSTFromPostorder(postorder, l, sp-1);
		root.right=buildBSTFromPostorder(postorder, sp, h-1);
		
		return root;
	}
	
	Node buildBSTFromPreorder(int[] preorder, int l, int h) {
		if(l>h) return null;
		Node root= new Node(preorder[l]);
		if(l==h) return root;
		
		int sp= findSeparationPoint(preorder, l+1, h, preorder[l]);
		root.left=buildBSTFromPreorder(preorder, l+1, sp-1);
		root.right=buildBSTFromPreorder(preorder, sp, h);
		
		return root;
	}
	
	int findSeparationPoint(int[] arr, int l, int h, int rootkey) {
		for(int i=l; i<=h; i++) {
			if(arr[i]> rootkey) {
				return i;
			}
		}
		return -1;
	}
	
	Node buildBSTFromInorder(int[] inorder, int l, int h) {
		if(l>h) return null;
		int mid= (l+h)/2;
		Node root= new Node(inorder[mid]);
		if(l==h) return root;
		root.left= buildBSTFromInorder(inorder, l, mid-1);
		root.right=buildBSTFromInorder(inorder, mid+1, h);
		return root;
	}
	
	
	void printInorderTree(Node root) {
		if(root==null)return;
		printInorderTree(root.left);
		System.out.print(root.key+ " ");
		printInorderTree(root.right);
	}

	public static void main(String[] args) {
		Node n= new Node(5);
		n.left= new Node(2);
		n.right= new Node(8);
		n.right.left= new Node(6);
		n.right.right= new Node(11);
		BinaryTreeProblem bp= new BinaryTreeProblem();
//		bp.deleteInBSTree(n, 6);
//		bp.printInorderTree(n);
		System.out.println("from post order");
		int arr[]= {2,6,9,12,11,8,5};
		Node root= bp.buildBSTFromPostorder(arr, 0, arr.length-1);
		bp.printInorderTree(root);
		
		System.out.println("\nfrom pre order");
		int preArr[]= {5,2,8,6,11,9,12};
		Node root1= bp.buildBSTFromPreorder(preArr, 0, preArr.length-1);
		bp.printInorderTree(root1);
		
		System.out.println("\nfrom in order\n");
		int inArr[]= {1,2,4,5,6,8,9,11,12};
		Node root2= bp.buildBSTFromInorder(inArr, 0, inArr.length-1);
		bp.printInorderTree(root2);
		
	}

}
class Node{
	int key;
	Node left;
	Node right;
	Node(int key){
		this.key=key;
	}
}