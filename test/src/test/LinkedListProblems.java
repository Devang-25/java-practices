package test;

public class LinkedListProblems {
	
	static Node reverseLinkedList(Node node) { // a->-b->c->d->
		if(node.next== null) return node; //e
		Node t= reverseLinkedList(node.next); //e
		node.next.next= node; // e->d
		node.next =null; // d.next=null
		return t; //e
	}
	
	static class Node{
		char c;
		Node next;
		Node(char ch){
			c=ch;
			next=null;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node n1= new Node('a');
		Node n2= new Node('b');
		
		Node n3= new Node('c');
		Node n4= new Node('d');
		Node n5= new Node('e');
		n1.next= n2;
		n2.next=n3;
		n3.next=n4;
		n4.next=n5;
		Node res= reverseLinkedList(n1);
		while(res !=null) {
			System.out.println(res.c);
			res= res.next;
		}

	}

}
