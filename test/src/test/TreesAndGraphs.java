package test;

import java.util.ArrayList;
import java.util.LinkedList;

 class Graph1{
	ArrayList<LinkedList<Node1>> adj;
	ArrayList<Node1> nodes;
	ArrayList<Node1> getNodes(){
		return nodes;
	}
}

 class Node1{
	String name;
	boolean state;
	LinkedList<Node1> getAdjacent(){
		return adj.get()
	}
}
enum State {
	Unvisited, Visited, Visiting;
}

public class TreesAndGraphs {

	/*
	 * 1)Route Between Node1s: Given a directed graph, design an algorithm to find
	 * out whether there is a route between two Node1s.
	 */


	boolean search(Graph g, Node1 start, Node1 end) {
		if(start==end)return true;

		LinkedList<Node1>q=new LinkedList<Node1>();
		for(Node1 u:g.getNodes())
		{
			start.state=true;
			q.add(start);
			Node1 u;
			while(!q.isEmpty()){
				u=q.removeFirst();
				if(u!=null){
					for(Node1 v:u.getAdjacent()){
						if(!v.state){
							if(v==end){
								return true;
							}else{
								v.state=true;
								q.add(v);
							}
						}
					}
				}
			}

		}
		return false;
	}

}
