package com.preety.designpatterns.singletonpattern;

import java.io.Serializable;

public class Singleton implements Serializable{
    public static final Singleton INSTANCE = new Singleton();
    private int value;
    
    private Singleton() {
    }
    private Singleton(int value) {
    }
    protected Object readResolve() {
        return INSTANCE;
    }
	public void setValue(int value) {
		this.value=value;
		
	}
	public int getValue() {
		return this.value;	
	}
}
