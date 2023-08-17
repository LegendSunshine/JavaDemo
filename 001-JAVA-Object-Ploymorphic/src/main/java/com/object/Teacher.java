package com.object;

/**
 * @ClassName Teacher
 * @Date 2023/5/15 22:08
 * @Author legend
 */
public class Teacher {

    private String name;
    private String career;
    private String  age;


    public void method1(){
        System.out.println("老师的名字");
    }

    public String getName() {
        return name;
    }

    public String getCareer() {
        return career;
    }

    public String getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
