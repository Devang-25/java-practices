package problems;

import problems.AmazonProblems.DLLNode;

public class LinkedListProblem {

	class Node {
		int key;
		Node next;

		Node(int key) {
			this.key = key;
		}
	}

	void printMidNode(Node head) {
		if (head == null)
			return;
		Node fast = head;
		Node slow = head;
		while (fast != null) {
			if (fast.next == null) {
				System.out.println(slow.key);
				break;
			} else if (fast.next.next == null) {
				System.out.println(slow.key);
				System.out.println(slow.next.key);
				break;
			}

			fast = fast.next.next;
			slow = slow.next;

		}
	}

	void printMidNode(Node head, int counter) {
		Node curr = head;
		Node mid = head;
		while (curr.next != null) {
			counter++;
			curr = curr.next;
			if (counter % 2 == 1) {
				mid = mid.next;

			}

		}
		if (counter % 2 == 1) {
			System.out.println(mid.key);
		} else {
			System.out.println(mid.key);
			System.out.println(mid.next.key);
		}

	}

	Node swapNodesPairWiseNode(Node head) {
		
		if (head == null || head.next == null)
			return head;

		Node prev= head;
		Node curr= head.next;
		Node next= head.next.next;
		
		head= curr;
		while(true) {
			curr.next= prev;
			if(next== null || next.next==null) {
				prev.next= next;
				break;
			}

			prev.next=next.next;
			prev=next;
			curr= prev.next;
			next= curr.next;
			
		}
		return head;
	}

	void swapNodesPairWise(Node head) {
		if (head == null || head.next == null)
			return;

		Node curr = head;

		// 3->4->5
		while (curr != null && curr.next != null) {
			int key = curr.key;
			curr.key = curr.next.key;
			curr.next.key = key;
			curr = curr.next.next;
		}

	}

	void swapNodesPairWiseRecursive(Node head) {
		if (head == null || head.next == null)
			return;
		swapNodesPairWiseRecursive(head, head);
	}

	void swapNodesPairWiseRecursive(Node head, Node curr) {
		if (curr == null || curr.next == null)
			return;
		int key = curr.key;
		curr.key = curr.next.key;
		curr.next.key = key;
		swapNodesPairWiseRecursive(head, curr.next.next);
	}

	
	Node deleteANodeInLinkedList(Node head, int  key) {
		if(head.key== key) {
			Node temp= head;
			head=head.next;
			temp.next=null;
			return head;
		}
		Node curr= head;
		
		while(curr!=null && curr.next!=null) {
			if(curr.next.key==key) {
				Node temp= curr.next;
				curr.next= curr.next.next;
				temp.next=null;
				break;
			}
			curr= curr.next;
		}
		return head;
		
	}
	
	void deleteANodeInLL(Node head, int  key) {
		
		if(head.key== key) {
			if(head.next==null) {
				head=null;
				return;
			}
			head.key= head.next.key;
			Node t= head.next;
			head.next= head.next.next;
			t.next=null;
			
			return;
		}
		System.out.println("hi");
		Node curr= head;
		while(curr.next!=null) {
			if(curr.next.key==key) {
				Node t= curr.next;
				curr.next= curr.next.next;
				t.next=null;
			}
			curr= curr.next;
		}
	}
	
	void printNthLastNode(Node head, int n) {
		Node curr= head;
		int size=0;
		while(curr!=null) {
			size++;
			curr=curr.next;
			
		}
		int m= size-n+1;
		Node p=head;
		while(m>1) {
			p=p.next;
			m--;
		}
		System.out.println(p.key);
		
	}
	
	void printNthLastN(Node head, int n) {
		if(head==null) return;
		Node curr=head;
		int count=0;
		while(count<n) {
			curr= curr.next;
			count++;
			if(curr==null) {
				return;
			}
		}
		Node prev= head;
		while(curr!=null) {
			prev= prev.next;
			curr= curr.next;
		}
		System.out.println(prev.key);
	}
	
	// 8) Reverse a linked list in groups of size k 
	// eg:  1->2->3->4->5->6->7->8, k=3 => 3->2->1->6->5->4->8->7
	Node reverseLinkedList(Node root, int k) {
		if(root==null || k==0) return root;
		int count= k;
		Node curr= root;
		Node prev=null;
		Node next=null;
		while(count>0 && curr!=null) {
			next=curr.next;
			curr.next=prev;
			prev=curr;
			curr=next;
			count--;
		}
		if(next!=null) {
			root.next= reverseLinkedList(next,k);
		}
		
		
		return prev;
		
		
	}
	
	// 9) Given two numbers represented by two linked lists, write a function that returns sum list
	
	
	 Node sumTwoNumbersInLL(Node n1, Node n2) {
		 if(n1==null) return n2;
		 if(n2==null) return n1;
		 Node curr=null;
		 Node head=null;
		 int carry=0;
		 
		 while(n1!=null || n2!=null) {
			 int val= carry;
			 if(n1!=null) {
				 val +=n1.key;
				 n1=n1.next;
			 }
			 if(n2!=null) {
				 val +=n2.key;
				 n2=n2.next;
			 }
			 
			 
			 carry= val/10;
			 val= val%10;
			 if(curr==null) {
				 head= new Node(val);
				 curr=head;
			 }else {
				 curr.next= new Node(val);
				 curr=curr.next;
			 }
		 }
		 if(n1== null && n2==null) {
			 if(carry!=0) {
				 curr.next= new Node(carry);
				 curr=curr.next;
			 }
		 } 
		 
		 return head;
	 }
	
	 void printList(Node head) 
	    { 
	        Node temp = head; 
	        while (temp != null) 
	        { 
	           System.out.print(temp.key+" "); 
	           temp = temp.next; 
	        }   
	        System.out.println(); 
	    } 
	
	public static void main(String[] args) {
		LinkedListProblem lp = new LinkedListProblem();
		Node n = lp.new Node(10);
		n.next = lp.new Node(5);
		n.next.next = lp.new Node(52);
		n.next.next.next = lp.new Node(55);
		 n.next.next.next.next = lp.new Node(15);

		// lp.printMidNode(n);
		// lp.printMidNode(n, 1);
		// lp.swapNodesPairWise(n);
		//lp.swapNodesPairWiseRecursive(n);
//		Node st= lp.swapNodesPairWiseNode(n);
//		while (st != null) {
//
//			System.out.println(st.key);
//			st = st.next;
//		}
		
//		Node st= lp.deleteANodeInLinkedList(n, 10);
//		while (st != null) {
//
//			System.out.println(st.key);
//			st = st.next;
//		}
		
//		lp.deleteANodeInLL(n, 10);
//		while (n != null) {
//
//			System.out.println(n.key);
//			n = n.next;
//		}
//		 
//		 lp.printNthLastNode(n, 4);
//		 lp.printNthLastN(n, 4);
		 
		 Node t= lp.reverseLinkedList(n, 2);
	//	 lp.printList(t);
		 Node l= lp.new Node(6);
		l.next=lp.new Node(5);
		
		Node k= lp.new Node(7);
		k.next=lp.new Node(8);
		k.next.next=lp.new Node(1);
		 Node m= lp.sumTwoNumbersInLL(l, k);
		 while (m != null) {
			 
 			System.out.print(m.key+" ");
 			m = m.next;
 		}
	}

}
