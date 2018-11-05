package test;

import java.util.HashSet;

public class CoinChange {
	
	static int getNumOfCoinCombinations(int[] coins, int sum) {
		int count=0;
		HashSet set= new HashSet();
		if(sum==0) {
			
			//System.out.println(sum);
			return 1;
		}
		if(sum<0) {
			//System.out.println(sum);
			return 0;
		}
		for(int i=0; i< coins.length; i++) {
		//	System.out.println(coins[i] +", "+sum);
			int result= getNumOfCoinCombinations(coins, sum-coins[i]);
			if(result==1) {
			//	System.out.print(coins[i] + " ");
				count++;
			//	System.out.println();
			}
			
			//count +=  ;// > 0 ? 1 : 0;
		}
		return count;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr= new int[] {3,5,10};
	        System.out.println(getNumOfCoinCombinations(arr,20));

	}
}
