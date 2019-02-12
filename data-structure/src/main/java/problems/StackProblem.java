package problems;

import java.util.LinkedList;

public class StackProblem {
	class Node {
		int key;
		int min;

		Node(int key) {
			this.key = key;
			this.min = key;
		}
	}

	class Stack<T> {
		LinkedList<Node> list;
		int size;

		Stack(int size) {
			this.size = size;
			list = new LinkedList<Node>();
		}

		boolean push(Node n) {
			if (list.size() == size)
				return false;
			if (list.size() >= 1) {
				Node lastNode = list.get(list.size() - 1);
				if (lastNode.min < n.key) {
					n.min = lastNode.min;
				}
			}
			return list.add(n);
		}

		Node pop() {
			if (list.size() == 0)
				return null;
			return list.removeLast();
		}

		int findMin() {
			return list.get(list.size() - 1).min;
		}

		boolean isEmpty() {
			return list.size() == 0;
		}
	}

	// implement stack using queue

	class Queue {
		Stack st1;
		Stack st2;

		Queue(int size) {
			st1 = new Stack(size);
			st2 = new Stack(size);
		}

		boolean enQueue(Node n) {

			return st1.push(n);
		}

		Node deQueue() {
			while (!st1.isEmpty()) {
				st2.push(st1.pop());
			}
			return st2.pop();
		}

	}

	public static void main(String[] args) {
		StackProblem sp = new StackProblem();
		Queue q = sp.new Queue(5);
		q.enQueue(sp.new Node(10));
		q.deQueue();
		q.enQueue(sp.new Node(110));
		q.enQueue(sp.new Node(11));
		q.enQueue(sp.new Node(120));
		q.enQueue(sp.new Node(12));
		// q.enQueue(sp.new Node(130));
		System.out.println(q.deQueue().key);
		
		Stack s= sp.new Stack(5);
		s.push(sp.new Node(10));
		s.push(sp.new Node(2));
		s.push(sp.new Node(5));
		s.push(sp.new Node(4));
		
		
		System.out.println(s.findMin());
		s.pop();
		s.pop();
		System.out.println(s.findMin());
		s.pop();
		System.out.println(s.findMin());
	}

}
