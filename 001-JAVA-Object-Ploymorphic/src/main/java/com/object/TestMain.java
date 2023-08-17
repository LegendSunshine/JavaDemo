package com.object;

/**
 * @ClassName TestMain
 * @Date 2023/5/15 22:12
 * @Author legend
 */
public class TestMain {

    public static void main(String[] args) {
        Teacher student = new Student();
        student.setName("student");
        Teacher teacher = new Teacher();
        teacher.setName("teacher");
        student.method1();
        System.out.println(student.getName());
    }
}
