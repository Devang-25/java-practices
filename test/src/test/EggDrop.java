package test;

public class EggDrop {

	/*
	 * Function to get minimum number of trials needed in worst case with n eggs and
	 * k floors
	 */
	static int eggDrop(int n, int k) {
		// If there are no floors, then
		// no trials needed. OR if there
		// is one floor, one trial needed.
		if (k == 1 || k == 0)
			return k;

		// We need k trials for one egg
		// and k floors
		if (n == 1)
			return k;

		int min = Integer.MAX_VALUE;
		int x, res;

		// Consider all droppings from
		// 1st floor to kth floor and
		// return the minimum of these
		// values plus 1.
		for (x = 1; x <= k; x++) {
			// System.out.println("x: "+x +", k: "+k+", n-1: "+(n-1) +", (k-x): "+(k-x));
			int n1 = eggDrop(n - 1, x - 1);
			int n2 = eggDrop(n, k - x);
			res = Math.max(n1, n2);
			// System.out.println("res: "+res +", n1: "+n1+ ", n2: " +n2);
			if (res < min)
				min = res;
		}

		return min + 1;
	}

	static int eggDropsDynamic(int n, int k) {
		int[][] results = new int[n + 1][k + 1];

		for (int i = 0; i <= n; i++) {

			for (int j = 0; j <= k; j++) {

				if (i == 0)
					results[i][j] = 0;
				else if (i == 1) {
					results[i][j] = j;
				} else if (j == 0 || j == 1) {
					results[i][j] = j;
				} else {
					int min = Integer.MAX_VALUE;
					for (int x = 1; x <= j; x++) {
						int n1 = results[i - 1][x - 1];
						int n2 = results[i][j - x];
						int res = Math.max(n1, n2);
						if (res < min) {
							min = res;
						}
					}
					results[i][j] = min+1;
				}

			}
		}
		System.out.println(results[n][k]);
		return results[n][k];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 2, k = 10;
		System.out.println("Minimum number of " + "trials in worst case with " + n + " eggs and " + k + " floors is "
				+ eggDropsDynamic(n, k));

	}

}
