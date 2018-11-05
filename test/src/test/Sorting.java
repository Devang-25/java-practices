package test;

public class Sorting {
	
	int[] MergeSort(int[]a) {
		int []aux= new int[a.length];
		MergeSort(a, aux, 0, a.length-1 );
		return aux;
	}
	void MergeSort(int[]a, int aux[], int l,  int h){
		if(h<=l) {
			return;
		}
		int m= l + (h-l)/2;
		MergeSort(a,aux, l, m);
		MergeSort(a,aux, m+1, h);
		merge(a,aux,  l,m,  h);
	}
	
	void merge(int[]a, int aux[], int lo, int mid,  int hi) {
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
	
	void printArray(int[] arr, int size) {
		for (int i = 0; i < size; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Sorting merge= new Sorting();
		int[] a= new int[] {2,4,1,5, 8, 7};
		int []aux= merge.MergeSort(a);
		
		merge.printArray(aux, a.length);
	}

}
