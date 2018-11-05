package test;

public class MergeSort {

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}
	
	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b)<0 ? true: false;
	}

	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	public static Comparable[] sort(Comparable[] a) {
		Comparable [] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
		return aux;
	}
	static void printArray(Comparable[] arr, int size) {
		for (int i = 0; i < size; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Comparable[] a= new Comparable[] {2,4,1,5, 8, 7};
		Comparable []aux= MergeSort.sort(a);
		
		printArray(aux, a.length);
	}


}
