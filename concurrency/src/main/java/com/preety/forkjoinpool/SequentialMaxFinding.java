package com.preety.forkjoinpool;

public class SequentialMaxFinding {
	
	public int sequentialMaxFind(int[] nums, int maxIndex) {
		int max= nums[0];
		for(int i=1; i<= maxIndex; i++) {
			if(nums[i]> max) {
				max= nums[i];
			}
		}
		return max;
	}

}
