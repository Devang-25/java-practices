package test;

public class KnapsackProblem {
	int max_val;

	static class Tuple {
		int wt;
		int val;

		Tuple(int wt, int val) {
			this.wt = wt;
			this.val = val;
		}
	}

	int knapsack(Tuple[] tuples, int n, int W) {
		if (n == 0 || W == 0) {
			return 0;
		}

		if (tuples[n - 1].wt > W) {
			return knapsack(tuples, n - 1, W);
		}

		return Math.max(tuples[n - 1].val + knapsack(tuples, n - 1, W - tuples[n - 1].wt), knapsack(tuples, n - 1, W));

	}

	int knapsackDP(Tuple[] tuples, int n, int W) {
		int[][] knapsack = new int[n + 1][W + 1];

		for (int i = 0; i <= n; i++) {
			for (int w = 0; w <= W; w++) {
				if (i == 0 || w == 0) {
					knapsack[i][w] = 0;
				} else {
					if (tuples[i - 1].wt <= w) {
						knapsack[i][w] = Math.max(tuples[i - 1].val + knapsack[i - 1][w - tuples[i - 1].wt],
								knapsack[i - 1][w]);

					} else {
						knapsack[i][w] = knapsack[i - 1][w];
					}
				}
			}
		}
		return knapsack[n][W];
	}

	int knapsackUnbound(Tuple[] tuples, int n, int W) {
		int K[] = new int[W + 1];

		for (int w = 0; w <= W; w++) {
			for (int i = 0; i < n; i++) {
				if (tuples[i].wt <= w) {

					K[w] = Math.max(tuples[i].val + K[w - tuples[i].wt], K[w]);
				}
			}
		}

		return K[W];

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Tuple[] ts = new Tuple[3];
		ts[0] = new Tuple(7, 160);
		ts[1] = new Tuple(3, 90);
		ts[2] = new Tuple(2, 15);

		// int v= new KnapsackProblem().knapsack(ts, 3, 10);
		// System.out.println(v);

//		int v1 = new KnapsackProblem().knapsackDP(ts, 3, 20);
//		System.out.println(v1);
//		
		int v2 = new KnapsackProblem().knapsackUnbound(ts, 3, 20);
		System.out.println(v2);

	}

}
