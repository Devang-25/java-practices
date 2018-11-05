package test;

public class Heap {
	static void min_heapify(int Arr[], int i, int N) {
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int smallest;
		if (left < N && Arr[left] < Arr[i])
			smallest = left;
		else
			smallest = i;
		if (right < N && Arr[right] < Arr[smallest])
			smallest = right;
		if (smallest != i) {
			swap(Arr, i, smallest);
			System.out.println("i: " + i + ", smallest: " + smallest);
			min_heapify(Arr, smallest, N);
		}
	}

	static void max_heapify(int[] arr, int i, int N) {
		int largest;
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		if (left < N && arr[left] > arr[i]) {
			largest = left;
		} else {
			largest = i;
		}
		if (right < N && arr[right] > arr[largest]) {
			largest = right;
		}

		if (largest != i) {
			swap(arr, i, largest);
			max_heapify(arr, largest, N);
		}
	}

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static void build_minheap(int Arr[], int N) {
		for (int i = N / 2; i >= 0; i--) {
			min_heapify(Arr, i, N);
			// printArray(Arr, N);
		}

	}

	static void build_maxheap(int arr[], int N) {
		for (int i = N / 2; i >= 0; i--) {
			max_heapify(arr, i, N);
		}
	}

	static void printArray(int[] arr, int size) {
		for (int i = 0; i < size; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	static void heapSort(int[] arr, int N) {
		build_maxheap(arr, N);
		System.out.println("print maxheap arr: ");
		printArray(arr, N);
		int heap_size = N;
		for (int i = N-1; i >= 1; i--) {
			swap(arr, 0, i);
			heap_size = heap_size - 1;
			max_heapify(arr, 0, heap_size);
			System.out.println("print heapified arr: ");
			printArray(arr, N);
		}
	}

	public static void main(String[] args) {
		int ar[] = new int[] { 4, 2, 6, 3, 5, 1, 8 };
		int size = ar.length;

//		build_minheap(ar, size);
//		printArray(ar, size);
//
//		build_maxheap(ar, size);
//		printArray(ar, size);

		heapSort(ar, size);
		printArray(ar, size);
	}
}
