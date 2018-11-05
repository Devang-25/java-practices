package test;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryMaze {

	static class Position {
		int i;
		int j;
		int dist;

		Position(int x, int y) {
			i = x;
			j = y;
		}
	}
	

	static int findShortestPaths(int[][] maze, Position x, Position y, int n, int m) {
		boolean visited[][] = new boolean[n][m];

		Queue<Position> q = new LinkedList<Position>();
		x.dist = 0;
		q.add(x);
		visited[x.i][x.j] = true;
		Position s = null;
		while (q.size() != 0) {

			s = q.poll();
			if (s.i == y.i && s.j == y.j) {
				break;
			}
			int left = s.j - 1 >=0 ? maze[s.i][s.j - 1] :-1;
			int right = s.j + 1 < m ? maze[s.i][s.j + 1] : -1;
			int bottom = s.i + 1 <n ? maze[s.i + 1][s.j] : -1;
			int up = s.i - 1 >=0 ? maze[s.i - 1][s.j] : -1;
			if (left == 1) {
				Position np = new Position(s.i, s.j - 1);
				np.dist = s.dist + 1;
				q.add(np);
				visited[s.i][s.j - 1] = true;
			}
			if (right == 1) {
				Position np = new Position(s.i, s.j + 1);
				np.dist = s.dist + 1;
				q.add(np);
				visited[s.i][s.j + 1] = true;
			}
			if (bottom == 1) {
				Position np = new Position(s.i + 1, s.j);
				np.dist = s.dist + 1;
				q.add(np);
				visited[s.i + 1][s.j] = true;
			}
			if (up == 1) {
				Position np = new Position(s.i - 1, s.j);
				np.dist = s.dist + 1;
				q.add(np);
				visited[s.i - 1][s.j] = true;
			}
		}

		if (s != null && (s.i != y.i || s.j != y.j)) {
			return -1;
		}
		return s.dist;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] binaryMaze = new int[][] {
				{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
				{ 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
				{ 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, 
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 }, 
				{ 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
				{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
				{ 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 } };
				
		Position x = new Position(0, 2);
		Position y = new Position(1, 4);

		int dist = findShortestPaths(binaryMaze, x, y, binaryMaze.length, binaryMaze[0].length);
		System.out.println(dist);

	}

}
