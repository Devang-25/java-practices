package test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFSDFSGraph {

	int V;
	LinkedList<Integer>[] adj;

	BFSDFSGraph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList();
		}
	}

	void addEdge(int u, int v) {
		adj[u].add(v);
	}

	void bfs(int u) {

		Queue<Integer> q = new LinkedList<Integer>();
		boolean visited[] = new boolean[V];

		q.add(u);
		visited[u] = true;
		while (q.size() != 0) {
			int s = q.poll();
			System.out.print(s + " ");
			LinkedList<Integer> list = adj[s];
			Iterator<Integer> i = list.iterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					q.add(n);
				}
				visited[n] = true;
			}
		}

	}

	void dfs(int n) {
		boolean[] visited = new boolean[V];
		visited[n] = true;
		dfsUtil(n, visited);

	}

	void dfsUtil(int n, boolean[] visited) {
		System.out.print(n + " ");
		LinkedList<Integer> list = adj[n];
		Iterator<Integer> i = list.iterator();	
		while (i.hasNext()) {
			int s = i.next();
			if (!visited[s]) {
				visited[s] = true;
				dfsUtil(s, visited);
			}
		}
	}

	public static void main(String[] args) {
		int size = 5;
		BFSDFSGraph g = new BFSDFSGraph(size);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		g.bfs(2);
		System.out.println();
		g.dfs(2);
	}

}
