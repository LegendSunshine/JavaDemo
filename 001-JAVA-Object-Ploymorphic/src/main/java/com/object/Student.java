package com.object;

/**
 * @ClassName Student
 * @Date 2023/5/15 22:08
 * @Author legend
 */
public class Student extends Teacher{

    private String name;
    private String age;

    public Student() {
    }

    public Student(String age) {
        this.age = age;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
