package test;

public class SortingAndSearching {

	public static void merge(int[] a, int[] b, int n, int m) {

		int k = m + n - 1; // Index of last location of array a

		int i = n - 1; // Index of last element in array a

		int j = m - 1; // Index of last element in array b

		// Start comparing from the last element and merge a and b

		while (i >= 0 && j >= 0) {

			if (a[i] > b[j]) {

				a[k--] = a[i--];

			} else {

				a[k--] = b[j--];

			}

		}

		while (j >= 0) {

			a[k--] = b[j--];

		}
	}

	public static boolean isUniqueChars(String str) {

		int checker = 0;

		for (int i = 0; i < str.length(); ++i) {

			int val = str.charAt(i) - 'a';

			if ((checker & (1 << val)) > 0)
				return false;

			checker |= (1 << val);

		}

		return true;
	}

	public static int updateBits(int n, int m, int i, int j) {

		int max = ~0; /* All 1’s */

		// 1’s through position j, then 0’s

		int left = max - ((1 << j) - 1);

		// 1’s after position i
		int right = ((1 << i) - 1);

		// 1’s, with 0s between i and j
		int mask = left | right;

		// Clear i through j, then put m in there
		return (n & mask) | (m << i);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int b[] = { 2, 3, 4 };
		int a[] = new int[7];
		a[0] = 5;
		a[1] = 6;
		a[2] = 7;
		a[3] = 8;
		// SortingAndSearching.merge(a,b,4,3);
		//
		// for(int i=0; i<7; i++) {
		// System.out.println(a[i]);
		// }

//		boolean unique = isUniqueChars("abcd");
//		System.out.println(unique);
		
//		int N= 1000000000, M = 10101, i = 2, j = 6;
//		int res= updateBits(N, M, i, j);
//		System.out.println(res);
//		System.out.println(N);
	}

}
