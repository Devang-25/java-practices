package problems;

public class DynamicProgProblem {
	/*
	 	Maximum sum such that no two elements are adjacent
		Given an array of positive numbers, find the maximum sum of a subsequence with the constraint 
		that no 2 numbers in the sequence should be adjacent in the array. 
		So 3 2 7 10 should return 13 (sum of 3 and 10) or 3 2 5 10 7 should return 15 (sum of 3, 5 and 7).
		Answer the question in most efficient way.
		
		Examples :
		
		Input : arr[] = {5, 5, 10, 100, 10, 5}
		Output : 110
		
		Input : arr[] = {1, 2, 3}
		Output : 4
		
		Input : arr[] = {1, 20, 3}
		Output : 20

	 */
	
	int maxSumSubseqRec(int[] arr, int  n) {
		
		int sum=0;
		if(n==0) return 0;
		if(n==1) return arr[0];
		
		return Math.max((arr[n-1] + maxSumSubseqRec(arr, n-2) ) , maxSumSubseqRec(arr, n-1) );	
	}
	
	int maxSumSubseqDP(int[] arr, int  n) {
		
		int[] sum= new int [n+1];
		sum[0]=0;

		for(int i=1; i<=n; i++) {
			
			for(int j=n-1; j>=i-1; j--) {
			//	System.out.println(j);
				 if(i-2<0) {
					sum[i]= Math.max(arr[j], sum[i-1]);// j 0->n-1, j=i-1
				} else {	
					sum[i]= Math.max((arr[j] + sum[i-2]), sum[i-1]);
				}
			}
			// System.out.println("sum[i]"+ i +": "+ sum[i]);
		}
		return sum[n];
	}
	
	int FindMaxSum(int arr[], int n) 
    { 
        int incl = arr[0]; 
        int excl = 0; 
        int excl_new; 
        int i; 
  //{5, 5, 10, 100, 100,200, 5, 60}
        // incl=5 5
        // excl=0 
     //excl_new=5 5
        for (i = 1; i < n; i++) 
        { 
            /* current max excluding i */
            excl_new = (incl > excl) ? incl : excl; 
  
            /* current max including i */
            incl = excl + arr[i]; 
            excl = excl_new; 
        } 
  
        /* return max of incl and excl */
        return ((incl > excl) ? incl : excl); 
    } 
	
	/*
	 	Edit Distance | DP-5
		Given two strings str1 and str2 and below operations that can performed on str1. Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
		
		Insert
		Remove
		Replace
		All of the above operations are of equal cost.

	 */
	
	 static int min(int x,int y,int z) 
	    { 
	        if (x<=y && x<=z) return x; 
	        if (y<=x && y<=z) return y; 
	        else return z; 
	    } 
	int minEdits(String str1, String str2) {
		if(str1==null && str2==null ) return 0;
		if(str1 == null || str1.length()==0) return str2.length();
		if(str2 == null || str2.length()==0) return str1.length();
		return minEdits(str1, str2, str1.length(), str2.length());
	}
	
	int minEdits(String str1, String str2, int n, int m) {
		
		if(n==0) return m;
		if(m==0) return n;
		if(str1.charAt(n-1)== str2.charAt(m-1)) {
			return minEdits(str1, str2, n-1, m-1);
		} 
		
		return 1+ min(minEdits(str1, str2, n, m-1), minEdits(str1, str2, n-1, m), minEdits(str1, str2, n-1, m-1));
		
	}
	
	int minEditsDP(String str1, String str2, int n, int m) {
		
		int[][] edits= new int[n+1][m+1];
		for(int i=0; i<n; i++) {
			edits[n][0]=n;
		}
		for(int i=0; i<m; i++) {
			edits[0][m]=m;
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m ; j++) {
				//System.out.println(i + ": " + j);
				if(str1.charAt(i-1)== str2.charAt(j-1)) {
					edits[i][j]= edits[i-1][j-1];
				}else {
					edits[i][j]= 1+ min(edits[i][j-1], edits[i-1][j], edits[i-1][j-1]);
				}
			}
		}
		return edits[n][m];
	}
	/*
	   Assembly Line Scheduling | DP-34
	 */

	public static void main(String[] args) {
		DynamicProgProblem dp= new DynamicProgProblem();
		int arr[]= {5, 5, 10, 100, 100,200, 5, 60};
		int n= arr.length;
//		int sum= dp.FindMaxSum(arr, n);
//		System.out.println(sum);
//		
//		int sumdp= dp.maxSumSubseqDP(arr, n);
//		System.out.println(sumdp);
		
		System.out.println(dp.minEdits("abc","abd"));
		System.out.println(dp.minEditsDP("abcd","abef", 4, 3));

	}

}
