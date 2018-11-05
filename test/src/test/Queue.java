package test;

public class Queue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedQueueOfStrings list= new LinkedQueueOfStrings();
		list.enqueue("hi");
		System.out.println(list.dequeue());
		System.out.println(list.dequeue());
		list.enqueue("hii");
		list.enqueue("hiii");
		System.out.println(list.dequeue());

	}

}

 class LinkedQueueOfStrings
{
 private Node first, last;

 private class Node
 { 
	  String item;
	  Node next;
 }


 public boolean isEmpty()
 { return first == null; }

 public void enqueue(String item)
 {
	 Node oldlast = last;
	 last = new Node();
	 last.item = item;
	 last.next = null;
	 if (isEmpty()) first = last;
	 else oldlast.next = last;
 }

 public String dequeue()
 {
	 if (isEmpty()) {
		 return null;
	 }
	 String item = first.item;
	 first = first.next;
	 if (isEmpty()) last = null;
	 return item;
 }
} 
