package com.example;

public class BaseTest {

    protected static int a =0;

    public static int getA() {
        return a;
    }

    public static void setA(int a) {
        BaseTest.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    private int b= 0;


    public void updateACount() {
       a++;
    }

}
