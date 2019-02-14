package problems;

public class ArrayProblem {

	int findMax3NumProduct(int[] arr) {
		if (arr == null || arr.length < 3)
			return -1;
		int n = arr.length;

		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		int max3 = Integer.MIN_VALUE;

		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			if (arr[i] > max1) {
				max3 = max2;
				max2 = max1;
				max1 = arr[i];
			} else if (arr[i] > max2) {
				max3 = max2;
				max2 = arr[i];
			} else if (arr[i] > max3) {
				max3 = arr[i];
			}

			if (arr[i] < min1) {
				min2 = min1;
				min1 = arr[i];
			} else if (arr[i] < min2) {
				min2 = arr[i];
			}

		}

		return Math.max(max1 * max2 * max3, max1 * min1 * min2);
	}

	/*
	 * The stock span problem is a financial problem where we have a series of n
	 * daily price quotes for a stock and we need to calculate span of stock’s price
	 * for all n days. The span Si of the stock’s price on a given day i is defined
	 * as the maximum number of consecutive days just before the given day, for
	 * which the price of the stock on the current day is less than or equal to its
	 * price on the given day. For example, if an array of 7 days prices is given as
	 * {100, 80, 60, 70, 60, 75, 85}, then the span values for corresponding 7 days
	 * are {1, 1, 1, 2, 1, 4, 6}
	 */

	int[] getStockSpan(int[] arr) {
		if (arr == null)
			return null;
		int n = arr.length;
		int[] result = new int[n];
		int c = 0;

		for (int i = 0; i < n; i++) {
			result[i] = 1;
			int j = i - 1;
			while (j >= 0) {
				c++;
				if (arr[j] < arr[i]) {
					result[i]++;
					j--;
				} else {
					break;
				}
			}

		}
		System.out.println("iterations: " + c);

		return result;
	}

	/*
	 * Next Greater Element Given an array, print the Next Greater Element (NGE) for
	 * every element. The Next greater Element for an element x is the first greater
	 * element on the right side of x in array. Elements for which no greater
	 * element exist, consider next greater element as -1.
	 * 
	 * Examples: a) For any array, rightmost element always has next greater element
	 * as -1. b) For an array which is sorted in decreasing order, all elements have
	 * next greater element as -1. c) For the input array [4, 5, 2, 25}, the next
	 * greater elements for each element are as follows.
	 * 
	 * Element NGE 4 --> 5 5 --> 25 2 --> 25 25 --> -1 d) For the input array [13,
	 * 7, 6, 12}, the next greater elements for each element are as follows.
	 * 
	 * Element NGE 13 --> -1 7 --> 12 6 --> 12 12 --> -1
	 */
	
	public int[] findNextGreaterElement(int[] arr) {
		if (arr == null)
			return null;
		int n = arr.length;
		int[] result = new int[n];
		
		int maxPos=0;
		int maxValue= -1;
		
		//[13, 7, 6, 12}
		for(int i=0; i<n-1; i++) {
			if(maxPos>i) {
					result[i]=maxValue;
				
			}else if(i!=0 && maxPos==i) {
				maxValue= -1;
				result[i]=maxValue;
			}
			else {
				int j=i+1;
				while(j<n) {
					if(arr[j]> arr[i] && arr[j]> maxValue) {
						maxPos= j;
						maxValue=arr[j];
						
					}
					j++;
				}
				result[i]=maxValue;
			}	
		}
		result[n-1]=-1;
		
		return result;
		
	}
	
	public static void main(String[] args) {
		// int[] arr = { 3, 2, 4, 5, 1, -6, 2 };
		ArrayProblem ap = new ArrayProblem();
		// System.out.println(ap.findMax3NumProduct(arr));
		//
//		int[] a = { 100, 80, 60, 70, 60, 75, 85 };
		//int[] result = ap.getStockSpan(a);
		
		int a1[]= {13, 7, 6, 12, 15,15, 14, 10, 11};
		int[] result1 = ap.findNextGreaterElement(a1);

		for (int i = 0; i < a1.length; i++) {
			System.out.print(result1[i] + " ");
		}

	}

}
