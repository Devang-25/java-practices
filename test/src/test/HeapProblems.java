package test;

public class HeapProblems {
	
	static int[] findFirstKLargestNum(int[] arr, int n, int k) {
		int[] result= new int[k];
		build_maxheap(arr, n);
		for(int i=n-1, j=0; j<k; i--, j++) {
			result[j]= arr[0];
			swap(arr, 0, i);
			max_heapify(arr, i,0);
		}
		return result;
	}
	
	static void heapsort(int[] arr, int n) {
		build_maxheap(arr, n);
		for(int i=n-1; i>0; i--) {
			swap(arr, 0, i);
			max_heapify(arr, i,0);
		}
	}
	
	static void max_heapify(int[] arr, int n, int i) {
		int left= 2*i +1;
		int right= 2*i +2;
		int largest= Integer.MIN_VALUE;
		if(left<n && arr[i]< arr[left]) {
			largest= left;
		} else {
			largest=i;
		}
		if(right<n && arr[largest]< arr[right]) {
			largest= right;
		} 
		if(largest !=i) {
			swap(arr, i, largest);
			max_heapify(arr, n, largest);
		}
	}
	
	static void build_maxheap(int[] arr, int n) {
		for(int i=(n/2 -1); i>=0; i--) {
			max_heapify(arr, n, i);
		}
	}
	
	static void swap(int [] a, int i, int j) {
		int t= a[i];
		a[i]= a[j];
		a[j]=t;
	}
	
	static void print(int arr[]) {
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
			
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int arr[] = new int[] {2,4,5,1,7,3,9};
		int n= arr.length;
		build_maxheap(arr, n);
		print(arr);
		int[] res= findFirstKLargestNum(arr, n, 3);
		print(res);
		
		heapsort(arr, n);
		print(arr);
		
	}

}
