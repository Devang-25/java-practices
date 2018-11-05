package test;

import java.util.Arrays;

public class PythagoreanTriplet {
	
	static boolean hasPythagoreanTriplet(int[] arr) {
		int n= arr.length;
		for(int i=0; i<n; i++) {
			arr[i]= arr[i]* arr[i];
		}
		Arrays.sort(arr);
		
		for(int i=n-1; i>=2; i--) {
			int l= 0;
			int r= i-1;
			while(l<r) {
				if(l+r== i) {
					return true;
				} else if(arr[l]+arr[r]< arr[i]) {
					l++;
				} else if(arr[l]+arr[r]> arr[i]){
					r--;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr= new int[] {3,4,6,5};
		System.out.println(hasPythagoreanTriplet(arr));

	}

}
