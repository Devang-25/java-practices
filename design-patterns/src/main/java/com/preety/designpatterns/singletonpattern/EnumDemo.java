package com.preety.designpatterns.singletonpattern;


public class EnumDemo {
    public static void main(String[] args) {
        SingletonEnum singleton = SingletonEnum.INSTANCE;
        System.out.println(singleton.getValue());
        singleton.setValue(2);
        System.out.println(singleton.getValue());
        
        SingletonEnum singleton2 = SingletonEnum.INSTANCE;
        System.out.println(singleton2.getValue());
        singleton2.setValue(3);
        System.out.println(singleton2.getValue());
        
        System.out.println(singleton== singleton2);
        
    }
}
