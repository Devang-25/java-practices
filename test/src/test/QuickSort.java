package test;

//Java program for implementation of QuickSort
class QuickSort {
	/*
	 * This function takes last element as pivot, places the pivot element at its
	 * correct position in sorted array, and places all smaller (smaller than pivot)
	 * to left of pivot and all greater elements to right of pivot
	 */
	// i.e after every partition pivot element is at it's correct position and
	// returns position of it.
	int partition(int arr[], int low, int high) {
		int i = low;
		int pivote = arr[high];
		// iterate till before high index and swap with ith element if it is smaller.
		// //descending order sorting
		// So all the element before ith position is smaller than it
		for (int j = low; j < high; j++) {
			if (arr[j] >= pivote) {

				swap(arr, i, j);
				i++;
			}
		}
		// Now swap pivot position (high) element with i.
		// so now all element before pivot element are smaller than i. so pivot element
		// is sorted.
		swap(arr, i, high);
		return i;
	}

	void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	/*
	 * The main function that implements QuickSort() arr[] --> Array to be sorted,
	 * low --> Starting index, high --> Ending index
	 */
	void sort(int arr[], int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}

	}

	int findKthLargestNumber(int arr[], int low, int high, int k) {
		if (low < high) {
			int pi = partition(arr, low, high);

			if (pi == k - 1) {
				return arr[k - 1];
			}
			if (pi < k - 1) {
				findKthLargestNumber(arr, pi + 1, high, k);
			}
			if (pi > k - 1) {
				findKthLargestNumber(arr, low, pi - 1, k);
			}

		}
		return arr[k - 1];
	}

	void findKLargestNumbers(int[] arr, int low, int high, int k) {
		if (low < high) {
			int pi = partition(arr, low, high);
			if(pi==k-1) {
				System.out.println("1");
				findKLargestNumbers(arr, low, pi - 1, k);
			} else {
				System.out.println("2");
				findKLargestNumbers(arr, low, pi - 1, k);
				findKLargestNumbers(arr, pi + 1, high, k);
			}
			
			
		}
	}

	int partition(int[] arr, int l, int h, int k) {
		int pivot =k < h ? k : l;
		int i= l-1;
		for(int j=h-1; j>=0; j--) {
			if(arr[j]> arr[pivot]) {
				i++;
				swap(arr, i,j);
			}
		}
		i++;
		swap(arr, i, pivot);
		return i;
	}
	
	void findKlargest(int [] arr,int l, int h, int k) {
		if(l<h) {
			int p= partition(arr, l,h, k);
			System.out.println("k largest");
			for(int i=0; i<k ;i++) {
				System.out.println(arr[i]);
			}
			
			findKlargestOrdered(arr,l , p-1);
			System.out.println("sortted array");
			for(int i=0; i<=h ;i++) {
				System.out.println(arr[i]);
			}
		}	
	}
	
	void findKlargestOrdered(int [] arr,int l, int h) {
		if(l<h) {
			int p= partition(arr, l,h);
			findKlargestOrdered(arr,l , p-1);
			findKlargestOrdered(arr,p+1,h);
		}
	}
	
	/* A utility function to print array of size n */
	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// Driver program
	public static void main(String args[]) {
		int arr[] = { 10, 7, 8, 9, 1, 5 };
		int n = arr.length;

		QuickSort ob = new QuickSort();
//		 ob.sort(arr, 0, n-1);
//
//		 System.out.println("sorted array");
//		 printArray(arr);

		// System.out.println("Kth largest");
		// int l= ob.findKthLargestNumber(arr, 0, n-1, 4);
		// System.out.println(l);
		int k=4;
//		ob.findKLargestNumbers(arr, 0, n - 1, k);
//		printArray(arr);
		
		ob.findKlargest(arr, 0, n - 1, k);
	}
}