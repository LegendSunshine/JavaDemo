package com.example;

public class AppMain {

    public static void main(String[] args) {
        BaseTest baseTest = new BaseTest();
        baseTest.updateACount();
        System.out.println(BaseTest.getA());
        BaseTest baseTest2 = new BaseTest();
        baseTest2.updateACount();
        System.out.println(BaseTest.getA());
    }
}
