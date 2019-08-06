package com.github.ponymaggie.guava;

import com.google.common.base.Optional;

public class GuavaOptionalDemo {

    public static void main(String[] args) {
        //guava的optional类，比util包下的optional封装的更好
        String s1 = null;
        String s2 = "test";
        Optional optional1 = Optional.fromNullable(s1);
        Optional optional2 = Optional.fromNullable(s2);
        System.out.println(optional1.isPresent());
        System.out.println(optional2.isPresent());

        //我们经常会这么使用
        if(optional1.isPresent()){
            System.out.println(optional1.get());
        }else{
            System.out.println("data is null");
        }

        // of，创建optional实例，如果引用是null会报异常
        Optional.of("apple");
//        Optional.of(null); //异常

        //获取引用
        System.out.println(optional1.or("nothing is herer"));//返回Optional所包含的引用，若引用缺失，返回指定的值
        System.out.println(optional1.orNull());//返回Optional所包含的引用，若引用缺失，返回null
        System.out.println(optional2.get());//返回Optional所包含的引用，若引用缺失，则抛出异常


    }
}
