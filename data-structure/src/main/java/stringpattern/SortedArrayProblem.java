package stringpattern;

public class SortedArrayProblem {
	/*
	 * 1) Given a sorted array, being rotated; Find an element in it. 
	 * 
	 */
	int calls = 0;

	int findEleInSortedRotatedArr(int[] arr, int num, int l, int h) {
		calls++;
		if (l > h)
			return -1;

		int mid = (l + h) / 2;
		if (num == arr[mid])
			return mid;
		// sorted left part
		else if (arr[l] <= arr[mid]) {
			// sorted part
			if (num >= arr[l] && num < arr[mid]) {
				return findEleInSortedRotatedArr(arr, num, l, mid - 1);
			}
			return findEleInSortedRotatedArr(arr, num, mid + 1, h);

			// soorted right part
		}
		if (num > arr[mid] && num <= arr[h]) {
			return findEleInSortedRotatedArr(arr, num, mid + 1, h);

		}
		return findEleInSortedRotatedArr(arr, num, l, mid - 1);

	}

	//2) Given a sorted array, being rotated; Give me sorted array.
	public void getSortedArrayFromRotated(int[] arr) {
		int start = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < arr[start]) {
				start = i;
				break;
			}
		}
		int extra[] = new int[start];
		for (int i = 0; i < start; i++) {
			extra[i] = arr[i];
		}
		for (int i = start; i < arr.length; i++) {
			arr[i - start] = arr[i];
		}
		for (int i = 0; i < start; i++) {
			arr[arr.length - start + i] = extra[i];
		}
	}

	void printArr(int arr[]) {
		for (int i = 1; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	public static void main(String[] args) {
		int[] arr = { 7, 8, 9, 1, 2, 3, 4, 5, 6 };
		SortedArrayProblem p = new SortedArrayProblem();
		int found = p.findEleInSortedRotatedArr(arr, 7, 0, 8);
		// int found= search(arr, 8, 0, 6);
		System.out.println("found: " + found);
		System.out.println(p.calls);

		p.getSortedArrayFromRotated(arr);
		p.printArr(arr);
	}

}
