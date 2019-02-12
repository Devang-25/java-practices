package problems;

public class ArrayProblem {
	
	int findMax3NumProduct(int[] arr) {
		if(arr==null|| arr.length<3) return -1;
		int n= arr.length;
		
		int max1=Integer.MIN_VALUE;
		int max2=Integer.MIN_VALUE;
		int max3=Integer.MIN_VALUE;
		
		int min1=Integer.MAX_VALUE;
		int min2=Integer.MAX_VALUE;
		
		for(int i=0; i<n; i++) {
			if(arr[i]>max1) {
				max3=max2;
				max2=max1;
				max1= arr[i];
			} else if(arr[i]>max2) {
				max3=max2;
				max2=arr[i];
			} else if(arr[i]>max3) {
				max3=arr[i];
			} 
			
			if(arr[i]<min1) {
				min2=min1;
				min1= arr[i];
			} else if(arr[i]<min2) {
				min2=arr[i];
			} 
			
			
		}
		
		return Math.max(max1*max2*max3, max1*min1*min2);
	}

	public static void main(String[] args) {
		int[] arr= {3,2,4,5,1,-6,2};
		ArrayProblem ap= new ArrayProblem();
		System.out.println(ap.findMax3NumProduct(arr));

	}

}
