package test;

//Java program to print DFS traversal from a given given graph
import java.io.*;
import java.util.*;

//This class represents a directed graph using adjacency list
//representation
public class Graph {
	private int V; // No. of vertices

	// Array of lists for Adjacency List Representation
	private LinkedList<Integer> adj[];

	// Constructor
	Graph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adj[v].add(w); // Add w to v's list.
		// adj[w].add(v);
	}

	// A utility function to print the adjacency list
	// representation of graph
	static void printGraph(Graph graph) { // System.out.println("graph.adjListArray[v]"+ graph.adjListArray[0]);
		for (int v = 0; v < graph.V; v++) {
			System.out.println("Adjacency list of vertex " + v);
			System.out.print("head");
			for (Integer pCrawl : graph.adj[v]) {
				System.out.print(" -> " + pCrawl);
			}
			System.out.println("\n");
		}
	}

	// A function used by DFS
	void DFSUtil(int v, boolean visited[]) {
		// Mark the current node as visited and print it
		visited[v] = true;
		System.out.print(v + " ");

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = adj[v].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n])
				DFSUtil(n, visited);
		}
	}

	// The function to do DFS traversal. It uses recursive DFSUtil()
	void DFS(int v) {
		// Mark all the vertices as not visited(set as
		// false by default in java)
		boolean visited[] = new boolean[V];

		// Call the recursive helper function to print DFS traversal
		DFSUtil(v, visited);
	}

	// prints BFS traversal from a given source s
	void BFS(int s, int initial) {
		// Mark all the vertices as not visited(By default
		// set as false)
		boolean visited[] = new boolean[V];
		int level[] = new int[V];

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it
		visited[s] = true;
		queue.add(s);
		level[s]= initial;
		while (queue.size() != 0 ) {
			// Dequeue a vertex from queue and print it
	
			s = queue.poll();
			
			if(initial==3) {
				break;
			}
			System.out.print(s + " ");

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			Iterator<Integer> i = adj[s].listIterator();
			initial++;
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
					level[n]= initial;
				}
			}
		}
		System.out.println("level: " + level[0]+level[1]+level[2]+level[3]);
	}

	// This function is a variation of DFSUtil() in
	// https://www.geeksforgeeks.org/archives/18212
	private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {

		// Mark the current node as visited and
		// part of recursion stack
		if (recStack[i]) {
			//System.out.println("recStack[i]: " + i + ", " + recStack[i]);
			return true;
		}
		if (visited[i]) {
			System.out.println("visited[i]: " + i + ", " + visited[i]);
			return false;
		}

		visited[i] = true;

		recStack[i] = true;
		List<Integer> children = adj[i];

		for (Integer c : children)
			if (isCyclicUtil(c, visited, recStack))
				return true;

		recStack[i] = false;

		return false;
	}

	// Returns true if the graph contains a
	// cycle, else false.
	// This function is a variation of DFS() in
	// https://www.geeksforgeeks.org/archives/18212
	private boolean isCyclic() {

		// Mark all the vertices as not visited and
		// not part of recursion stack
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];

		// Call the recursive helper function to
		// detect cycle in different DFS trees
		boolean flag = false;
		for (int i = 0; i < V; i++)
			if (isCyclicUtil(i, visited, recStack)) {
				//System.out.println("cyclic recStack[i]: " + i + ", " + recStack[i]);
				flag = true;
			}

		return flag;
	}

	// prints BFS traversal from a given source s
	Boolean isReachable(int s, int d) {
		LinkedList<Integer> temp;

		// Mark all the vertices as not visited(By default set
		// as false)
		boolean visited[] = new boolean[V];

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it
		visited[s] = true;
		queue.add(s);

		// 'i' will be used to get all adjacent vertices of a vertex
		Iterator<Integer> i;
		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			s = queue.poll();

			int n;
			i = adj[s].listIterator();

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			while (i.hasNext()) {
				n = i.next();

				// If this adjacent node is the destination node,
				// then return true
				if (n == d)
					return true;

				// Else, continue to do BFS
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}

		// If BFS is complete without visited d
		return false;
	}

	// Driver code
	public static void main(String[] args) {

		Graph g = new Graph(5);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(2, 1);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		// printGraph(g);

		// DFS
		// System.out.println("Following is Depth First Traversal "+
		// "(starting from vertex 0)");
		//
		// g.DFS(0);

//		System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");
//		g.BFS(2,0);

		// graph has cycle
		 if (g.isCyclic())
		 System.out.println("Graph contains cycle");
		 else
		 System.out.println("Graph doesn't " + "contain cycle");

		// In directed graph has path between two vertices
//		int u = 1;
//		int v = 3;
//		if (g.isReachable(u, v))
//			System.out.println("There is a path from " + u + " to " + v);
//		else
//			System.out.println("There is no path from " + u + " to " + v);
//		;
//
//		u = 3;
//		v = 1;
//		if (g.isReachable(u, v))
//			System.out.println("There is a path from " + u + " to " + v);
//		else
//			System.out.println("There is no path from " + u + " to " + v);

	}
}
