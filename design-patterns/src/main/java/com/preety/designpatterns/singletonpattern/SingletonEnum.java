package com.preety.designpatterns.singletonpattern;

public enum SingletonEnum {
    INSTANCE, INSTANCE2;
    int value;
    
    private SingletonEnum() {}
    private SingletonEnum(int value) {
		this.value = value;
	}
	public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}