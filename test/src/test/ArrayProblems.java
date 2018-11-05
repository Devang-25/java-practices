package test;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ArrayProblems {

	static int findOccurancesOfANumInSortedArr(int[] arr, int num) {
		int count = 0;
		count = binarySearch(arr, num, 0, arr.length - 1);
		return count;
	}

	static int binarySearch(int[] arr, int num, int l, int h) {
		int mid = (l + h) / 2;
		int count = 0;
		while (l < h) {
			if (arr[mid] == num) {
				count++;
				for (int i = mid - 1, j = mid + 1; i >= 0 && j <= arr.length; i--, j++) {
					if (arr[i] == num)
						count++;
					if (arr[j] == num)
						count++;
				}
				break;
			} else if (num < arr[mid]) {
				h = mid - 1;
			} else if (num > arr[mid]) {
				l = mid + 1;
			}
		}

		return count;
	}

	static int findOccurances(int[] arr, int num) {
		int count=0;
		for(int i=0; i< arr.length; i++) {
			if(arr[i]<num) {
				continue;
			}
			if(arr[i]==num) {
				count++;
			}
			if(arr[i]>num) {
				break;
			}
		}
		return  count;
	}
	
	static void FindPairsForSum(int[] arr, int x) {
		HashSet<Integer> numset = new HashSet();

		for (int i = 0; i < arr.length; i++) {
			if (!numset.add(x - arr[i])) {
				numset.add((x - arr[i]));
				System.out.println("pairs: " + (x - arr[i]) + ", " + arr[i]);
			} else {
				numset.add(arr[i]);
			}
		}

	}

	static int binomialCoeff(int m, int k) {
		int res = m;
		for (int i = 1; i < k; i++) {
			res *= (m - i);
			res /= i;
		}

		return res / k;
	}

	static int catalanNumber(int n) {
		int res = binomialCoeff(2 * n, n);
		return res / (n + 1);

	}

	static int catalanNum(int n) {
		int cn[] = new int[n + 1];
		cn[0] = 1;
		cn[1] = 1;
		for (int i = 2; i <= n; i++) {
			cn[i] = 0;
			for (int j = 1; j <= i; j++) {
				cn[i] += cn[j - 1] * cn[i - j];
			}

		}
		return cn[n];

	}

	static int BSTs(int n) {
		return catalanNumber(n);

	}

	static int BTs(int n) {
		int cn = catalanNumber(n);
		for (int i = 1; i <= n; i++) {
			cn *= i;
		}
		return cn;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] { 1, 2, 2, 2, 3, 4, 5, 6 };
		// System.out.println(new Date().getTime());
		// FindPairsForSum(arr, 8);
		// System.out.println(new Date().getTime());

		// System.out.println(catalanNum( 4));
		
		int c = findOccurancesOfANumInSortedArr(arr, 2);
		
		System.out.println(c);
		System.out.println(new Date().getTime());
		int c1= findOccurances(arr, 2);
		System.out.println(new Date().getTime());
		System.out.println(c1);
		
	}

}
