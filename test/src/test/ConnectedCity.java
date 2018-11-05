package test;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


public class ConnectedCity {
	static ArrayList<Edge>[] adj;
	static boolean[] hasX;
	static int N, K;
	static long res = 0;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		/*
			5 3
			2 1 8
			1 0 5
			2 4 5
			1 3 4
			2
			4
			0
		 */
		N =  5;
		K =  3;
		adj = new ArrayList[N];
		hasX = new boolean[N];
		for(int i=0; i<N; i++){
			adj[i] = new ArrayList<Edge>();
		}
		
		for(int i=0; i<N-1; i++){
			int roads[][]= {{2,1,8},{1,0,5},{2,4,5}, {1,3,4}};
			int from = roads[i][0];
			int to = roads[i][1];
			int weight = roads[i][2];
			adj[from].add(new Edge(to, weight));
			adj[to].add(new Edge(from, weight));
		}
		
		for(int i=0; i<K; i++){
			int machines[]= {2,4,0};
			hasX[machines[i]] = true;
		}
		
		res = 0;
		dfs(0, 0, 0);
		System.out.println(res);
	}
	
	static int dfs(int cur, int parent, int parentWeight){
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
				
		for(Edge to : adj[cur]){
			if(to.to != parent){
				int cr = dfs(to.to, cur, to.weight);
			//	System.out.println("cr: "+ cr + ", cur: "+ cur + ", to.to: "+ to.to +", to.weight: "+ to.weight);
				if(cr > 0){
					pq.add(cr);
				}
			}
		}
		
		int min = 0;
		while(!pq.isEmpty()){
			res += min;
			min = pq.remove();
			System.out.println("min: "+ min + ", res: "+ res );
		}
		
		if(hasX[cur]){
			res += min;
		//	System.out.println("min: "+ min + ", res: "+ res);
			return parentWeight;
		} else if(min>0){
			return Math.min(min, parentWeight);
		} else{
			return 0;
		}
	}
	
	static class Edge{
		public int to;
		public int weight;
		
		public Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
}
