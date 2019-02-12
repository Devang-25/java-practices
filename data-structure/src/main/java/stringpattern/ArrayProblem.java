package stringpattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayProblem {
	/*
	 * Given an array of numbers, give me a wave. Input: a0 a1 a2 .. an-1
	 * Mathematical expression for wave: ai>=aj<=ak>=al<=am for all i, j,k,l .. m
	 * belong to [0,n-1]
	 */
	// [5,2,4,1,6,7]
	// 2>=1<=4>=3<=5

	void printArrayWave(int[] arr) {

		for (int i = 1; i < arr.length; i++) {
			if (i % 2 == 1 && arr[i] > arr[i - 1]) {
				int t = arr[i - 1];
				arr[i - 1] = arr[i];
				arr[i] = t;
			}
			if (i % 2 == 0 && arr[i] < arr[i - 1]) {
				int t = arr[0];
				arr[0] = arr[i];
				arr[i] = t; //
			}

		}

		System.out.print(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if (i % 2 == 1) {
				System.out.print(">=" + arr[i]);
			} else {
				System.out.print("<=" + arr[i]);
			}

		}
	}

	/*
	 * Given an array a[], find three indices (triplets) i,j,k such that: 1. i < j <
	 * k 2. a[i] < a[j] < a[k] 3. a[i] + a[j] + a[k] <= t , where t is a given sum
	 * Array is not necessarily sorted. Have to count number of such triplets.
	 * 
	 */

	class Triplet {
		int values[] = new int[3];

		@Override
		public String toString() {
			return "Triplet [values=" + Arrays.toString(values) + "]";
		}

	}

	List findTripletsWithSum(int[] arr, int t) {
		List<Triplet> triplets = new ArrayList<Triplet>();
		int n = arr.length;
		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				for (int k = j + 1; k < n; k++) {
					if (arr[i] < arr[j] && arr[j] < arr[k] && arr[i] + arr[j] + arr[k] <= t) {
						Triplet tr = new Triplet();
						tr.values[0] = arr[i];
						tr.values[1] = arr[j];
						tr.values[2] = arr[k];
						triplets.add(tr);
					}
				}
			}
		}

		return triplets;
	}

	List<Triplet> findTripletsWithSum1(int[] arr, int t) {
		if (arr == null || arr.length < 3)
			return null;
		int n = arr.length;
		Arrays.sort(arr);

		List<Triplet> triplets = new ArrayList<Triplet>();
		for (int i = 0; i < n - 2; i++) {
			int j = i + 1;
			int k = n - 1;
			while (k > j) {
				System.out.println(" it: " + i + ", j: " + j + ", k: " + k);
				if (arr[i] + arr[j] + arr[k] > t) {
					k--;
				} else if (arr[i] + arr[j] + arr[k] < t) {
					j++;
				} else {
					Triplet tr = new Triplet();
					tr.values[0] = arr[i];
					tr.values[1] = arr[j];
					tr.values[2] = arr[k];
					triplets.add(tr);
					j++;
					k--;
				}
			}
		}
		return triplets;
	}

	// array without duplicate numbers
	long countTripletsWithRatio(int[] arr, int r) {
		if (arr == null || arr.length < 3)
			return -1;
		int n = arr.length;
		Arrays.sort(arr);

		Set<String> triplets = new HashSet<String>();

		for (int i = 0; i < n - 2; i++) {
			int j = i + 1;
			int k = n - 1;
			while (k > j) {
				System.out.println(" it: " + arr[i] + ", j: " + arr[j] + ", k: " + arr[k]);
				if ((double) arr[j] / arr[i] == (double) r && (double) arr[k] / arr[j] > (double) r) {

					k--;
				} else if ((double) arr[j] / arr[i] < r) {
					j++;
				} else if ((double) arr[j] / arr[i] == r && (double) arr[k] / arr[j] == r) {
					String tr = arr[i] + ":" + arr[j] + ":" + arr[k];
					triplets.add(tr);
					break;
				}
			}
		}
		return triplets.size();
	}

	// array with duplicate numbers in GP
	void printTripletsWithDupsinGP(int[] arr, int n) {

		for (int i = 0; i < n-2; i++) {

			// Initialize i and k for
			// the current j
			int j = i+1;
			int k = n-1;

			// Find all i and k such that
			// (i, j, k) forms a triplet of GP
			while (j<k) {

				// # if arr[j]/arr[i] = r and
				// # arr[k]/arr[j] = r and r
				// # is an integer (i, j, k) forms
				// # Geometric Progression
				if (arr[j] % arr[i] == 0 && arr[k] % arr[j] == 0 && arr[j] / arr[i] == arr[k] / arr[j]) {

					// # print the triplet
					System.out.println(arr[i] + " " + arr[j] + " " + arr[k]);

					// # Since the array is sorted and
					// # elements are distinct.
					if(arr[j]== arr[j+1]) {
						j++;
					} else if(arr[k-1]== arr[k]) {
						k--;
					} else {
						break;
					}
				}
				// # if arr[j] is multiple of arr[i]
				// # and arr[k] is multiple of arr[j],
				// # then arr[j] / arr[i] != arr[k] / arr[j].
				// # We compare their values to
				// # move to next k or previous i.
				else if (arr[j] % arr[i] == 0 && arr[k] % arr[j] == 0) {

					if (arr[j] / arr[i] < arr[k] / arr[j])
						j++;
					
					else
						k--;
				}
				// # else if arr[j] is multiple of
				// # arr[i], then try next k. Else,
				// # try previous i.
				else if (arr[j] % arr[i] == 0)
					k += 1;
				else
					i -= 1;
			}
		}

	}

	/*
	 * Given an array of positive integers of size n. Find the maximum sum of
	 * triplet( ai + aj + ak ) such that 0 <= i < j < k < n and ai < aj < ak.
	 */
	// Function to calculate maximum triplet sum
	int maxTripletSum(int arr[], int n) {
		// Initialize the answer
		int ans = 0;

		for (int i = 1; i < n - 1; ++i) {
			int max1 = 0, max2 = 0;

			// find maximum value(less than arr[i])
			// from 0 to i-1
			for (int j = 0; j < i; ++j)
				if (arr[j] < arr[i])
					max1 = Math.max(max1, arr[j]);

			// find maximum value(greater than arr[i])
			// from i+1 to n-1
			for (int j = i + 1; j < n; ++j)
				if (arr[j] > arr[i])
					max2 = Math.max(max2, arr[j]);

			// store maximum answer
			ans = Math.max(ans, max1 + arr[i] + max2);
		}

		return ans;
	}

	public static void main(String[] args) {
		ArrayProblem ap = new ArrayProblem();
		int[] arr = {1 ,5, 5, 25, 125 };
		// ap.printArrayWave(arr);

		// List trip = ap.findTripletsWithSum1(arr, 12);
		// System.out.println(trip.size());
		// if (trip != null) {
		// System.out.println(trip.get(2) );
		// }

		 System.out.println(ap.countTripletsWithRatio(arr, 2));
		// System.out.println(ap.countTripletsWithDupsWithRatio(arr, 2));
		ap.printTripletsWithDupsinGP(arr, 5);
	}
}
