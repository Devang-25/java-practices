package test;

public class DijkstraShortestPath {
	
	static void printArray(int[] arr, int size) {
		for (int i = 0; i < size; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	static int findMinDist(boolean visited[], int[] dist)
	{
		int min= Integer.MAX_VALUE;
		int min_index = -1;
		for(int i=0; i< dist.length; i++) {
			if(dist[i]<= min && !visited[i]) {
				min= dist[i];
				min_index=i;
			}
		}
		return min_index;
	}
	

	static void dijkstra(int graph[][], int src) {
	
		int n= graph.length;
		boolean visited[] = new boolean[n];
		
		
		int[] dist= new int[n];
		for(int i=0; i<n; i++) {
			dist[i]= Integer.MAX_VALUE;
		}
		
		dist[src]= 0;
		
		for(int k=0; k< n-1; k++) {
			int u= findMinDist(visited, dist);
			// visited[u] is true once we have min dist for u.
 			visited[u]= true;
		//	System.out.println("u: " + u );
			
			for(int v=src; v< n; v++) {
				if(!visited[v]  && graph[u][v]!=0 && dist[u]!=Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
					dist[v]= dist[u] + graph[u][v];
					System.out.println("dist[v]), u, v, visited[u]: " + dist[v] +", "+ u +", "+ v  + ", visited[u]" + visited[u]);
				} 
			}
		}

		printArray(dist, n);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int graph[][] = new int[][] { 
			{ 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
			{ 4, 0, 8, 0, 0, 0, 0, 11, 0 },
			{ 0, 8, 0, 7, 0, 4, 0, 0, 2 },
			{ 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
			{ 0, 0, 0, 9, 0, 10, 0, 0, 0 },
			{ 0, 0, 4, 14, 10, 0, 2, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 2, 0, 1, 6 }, 
			{ 8, 11, 0, 0, 0, 0, 1, 0, 7 },
			{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
		DijkstraShortestPath t = new DijkstraShortestPath();
		t.dijkstra(graph, 0);

	}

}
