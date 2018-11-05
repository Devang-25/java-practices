package test;

public class NthStair {
	// n is total number of stairs and m is the max steps a person can take at a time. 
	//m=3 implies 1, 2 or 3 steps at a time
	static int totalwaysToReachNthStair(int n, int m) {
		int total=0;
		if(n<=1) {
			return 1;
		}
		for(int i=1; i<= n && i<=m; i++) {
			total +=totalwaysToReachNthStair(n-i,m);
		}
		return total;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=10;
		int m=3;
		int steps= totalwaysToReachNthStair(n, m);
		System.out.println(steps);
	}

}
