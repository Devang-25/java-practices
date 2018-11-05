package test;

import java.util.Arrays;
import java.util.Collections;

public class Array {
	
	
//	Input - [3, 2, 1, 5, 4, 0]  result   =  [0] + [1] + [2 * 3] + [4*5] = 27    5,4, 3,2, 1,  0, -1, -2, -3
//			Input - [3, 4, 2, 5, 1]      result 	  =  1 + [2 * 3] + [4*5] = 27
//			Input - [-3, 2, -1, 0]        result = [0] + [-1 * -3] + [2] = 5

	
	// 5,4,3,2				5*4+3*2
	// 5,4,3,2,1 			5*4+3*2+1
	// 5,4,3,2,0 			5*4+3*2+0
	// 5,4,3,2,1,0			5*4+3*2+1+0
	// 5,4,3,2,0,-1			5*4+3*2+0*-1
	// 5,4,3,2,1,0,-1		5*4+3*2+1+0*-1
	// 5,4,3,2,0,-1,-2		5*4+3*2+0+(-1*-2)
	// 5,4,3,2,1,0,-1,-2	5*4+3*2+1+0+(-1*-2)
	
	static int getMaxProductSum(Integer[] arr) {
		int n= arr.length;
		if(n==0) {
			return 0;
		}
		if(n==1) {
			return arr[0];
		}
		
		Arrays.sort(arr, Collections.reverseOrder());
		
		int sum=0;
		int i=0;
		for(; i<n; i+=2) {
			if(arr[i]<0 && n-i%2==0) {
				sum +=arr[i]* arr[i+1];
			} else if(arr[i]<0 && n%2==1) {
			   if(i==n-1) {
				   sum+=arr[i];
			   } else {
				   sum += arr[i]* arr[i+1];
			   }
			}else if(i<n-1 && arr[i]>0 && arr[i+1]<=0) {
				if(i+1 < n && (n-1-i-1)%2==1) { // 3,2,1,-1,-2 
					sum+=arr[i];
					i--;
				}else if(i+1 < n && (n-1-i-1)%2==0) { // 3,2,1,-1
					sum+=arr[i];
					sum+=arr[i+1];
				}
				
			}else if(i==n-1){
				sum += arr[i];
			} 
			else if(i<n-1){
				sum += arr[i]* arr[i+1];
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer [] arr= new Integer[]{3, 2, 1, 5, 4,0,-1,-2,-3};
		int sum= getMaxProductSum(arr);
		System.out.println(sum);

	}

}
