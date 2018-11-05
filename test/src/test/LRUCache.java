package test;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class LRUCache {
	


	    // store keys of cache
	    Deque <Integer> deque;
	 
	    // store references of key in cache
	    Set<Integer> set;
	    int csize; //maximum capacity of cache
	 

	 
	LRUCache(int n)
	{
	    csize = n;
	    deque= new LinkedList<Integer>();
	    set= new HashSet<Integer>();
	}
	 
	/* Refers key x with in the LRU cache */
	void refer(int x)
	{
	    // not present in cache
	    if (set.add(x))
	    {
	        // cache is full
	        if (deque.size() == csize)
	        {
	            //delete least recently used element
	            int deleted= deque.pollLast();
	            set.remove(deleted);
	        }
	    }// present in cache
	    else {
	    	deque.remove(x);
	    }
	    	
	 
	    // update reference
	    deque.offerFirst(x);
	}
	 
	// display contents of cache
	void display()
	{
		Iterator<Integer> it= deque.iterator();
		
	    while (it.hasNext())
	        System.out.println( it.next() + " ");
	}
	
	// implementation using LinkedHashMap, hashmap implementation using doubly linked list. keep order of insertion
	static class LRUCacheLinkedList<K,V> extends LinkedHashMap<K, V> {
		private static final long serialVersionUID= 100L;
		private int size;
		LRUCacheLinkedList(int size){
			super(size, .75f, true);
			this.size= size;
		}
		
		public static<K,V> LRUCacheLinkedList<K,V> newInstance(int size) {
			return new LRUCacheLinkedList<K,V>(size);
		}
		
		@Override
		protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
			return size()> size;
		}
		
	}
	 
	
	static class LRUCacheDLL{
		Node head;
		Node last;
		int size;
		HashSet<Integer> set;
		LRUCacheDLL(int size){
			this.size= size;
			set= new HashSet<Integer>(size);
			head=null;
			last=null;
		}
		
		void find(int x) {
			if(set.add(x)) {
				// System.out.println( size() + ":size ");
				if(size() >= size-1) {
					Integer rm=removeLast();
					set.remove(rm);
				}
			}else {
				remove(x);
				
			}
			addFirst(x);
			System.out.println( head.value + ":head ");
		}
		
		 int size() {
			int c=0;
			Node current= head;
			while(current !=null) {
				c++;
				current= current.next;
			}
			return c;
		}
		
		boolean addFirst(int value) {
			if(head== null) {
				head= new Node(value);
				last = head;
				return true;
			}
			head.prev= new Node(value);
			head.prev.next= head;
			head=head.prev;

			return true;
		}
		
		Integer removeLast() {
			if(last== null) {
				return null;
			}
			if(last.prev== null) {
				return last.value;
			}
			Integer rm= last.value;
			last= last.prev;
			last.next=null;
			// System.out.println( last.value + ":last , last.next: "+ last.next);
			return rm;
		}
		
		boolean remove(int value) {
			Node current= head;
			while(current.next.value != value) {
				if(current.next == null) {
					return false;
				}
				current=current.next;
			}
			current.next= current.next.next;
			if(current.next !=null) {
				current.next.prev= current;
			}
			
			return true;
		}
		
		
		
	}
	
	static class Node{
		int value;
		Node prev;
		Node next;
		Node(int key){
			value=key;
			prev=null;
			next=null;
		}
	}
	// Driver program to test above functions
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 LRUCache ca= new LRUCache(4);
		// LRUCacheLinkedList la= new LRUCacheLinkedList(4);
		 LRUCacheDLL ld= new LRUCacheDLL(8);
		 
		    ca.refer(1);
		    ca.refer(1);
		    ca.refer(2);
		    ca.refer(3);
		    ca.refer(1);
		    ca.refer(4);
		    ca.refer(5);
		    ca.refer(1);
		    ca.display();

//		 la.put(1, "hello");
//		 la.put(2, "hi");
//		 la.put(1, "hello");
//		 la.put(4, "hi");
//		 la.put(5, "hey");
//		 
//		 Iterator<Integer> it= la.keySet().iterator();
		 
//		 ld.find(1);
//		 ld.find(3);
//		 ld.find(2);
//		 ld.find(1);
//		 ld.find(4);
//		 ld.find(5);
//		 ld.find(6);
//		 ld.find(7);
//		 ld.find(8);
//		 ld.find(9);
//		 
//		 Node current= ld.head;
//		 System.out.println( ld.size() + ":size ");
//		    while (current!=null) {
//		    	System.out.println( current.value + " ");
//		    	current= current.next;
//		    	
//		    }
		        
	}

}


