package com.preety.forkjoinpool;

import java.util.concurrent.RecursiveTask;

public class ParallelMaxFinding extends RecursiveTask<Integer>{
	private int[] nums;
	private int lowIndex;
	private int highIndex;
	

	public ParallelMaxFinding(int[] nums, int lowIndex, int highIndex) {
		super();
		this.nums = nums;
		this.lowIndex = lowIndex;
		this.highIndex = highIndex;
	}


	@Override
	protected Integer compute() {
		if(highIndex-lowIndex< 2) {
			
		}
		return null;
	}

}
