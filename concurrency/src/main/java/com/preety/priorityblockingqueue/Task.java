package com.preety.priorityblockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task {
	private Character taskId;
	private int priority;
	private int executionTime;
	private List<Character> dependencies;
	Random random= new Random();

	public Task(Character taskId, int priority) {
		super();
		this.taskId = taskId;
		this.priority = priority;
		dependencies= new ArrayList<Character>();
		executionTime= random.nextInt(5000);
	}

	public Character getTaskId() {
		return taskId;
	}

	public void setTaskId(Character taskId) {
		this.taskId = taskId;
	}

	public int getPriority() {
		return priority;
	}
	
	public boolean addDependency(Character ch) {
		return dependencies.add(ch);
	}
	
	public boolean removeDependency(Character ch) {
		return dependencies.remove(ch);
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", priority=" + priority + ", executionTime=" + executionTime + "]";
	}

	
}
