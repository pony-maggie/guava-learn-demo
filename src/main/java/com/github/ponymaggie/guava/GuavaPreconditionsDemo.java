package com.github.ponymaggie.guava;

import com.google.common.base.Preconditions;

//Preconditions优雅的检验参数
public class GuavaPreconditionsDemo {

    public static void main(String[] args) {

        GuavaPreconditionsDemo demo = new GuavaPreconditionsDemo();
        try {

            demo.addPerson(-1, "23pony");

        }catch (Exception e){

            System.out.println("抛出异常了:" + e.getMessage());
        }

        try {

            demo.addPerson(10, "");

        }catch (Exception e){

            System.out.println("抛出异常了:" + e.getMessage());
        }

        try {

            demo.addPerson(20, null);

        }catch (Exception e){

            System.out.println("抛出异常了:" + e.getMessage());
        }

        try {

            demo.addPerson(30, "pony");

        }catch (Exception e){

            System.out.println("抛出异常了:" + e.getMessage());
        }

    }

    private void addPerson(int age, String name){
        Preconditions.checkNotNull(name, "姓名不能为空");
        Preconditions.checkArgument(name.length()>0, "姓名必须大于0");
        Preconditions.checkArgument(age>0, "年龄必须大于0");
        System.out.println("age= " + age + " name= " + name);


    }
}
