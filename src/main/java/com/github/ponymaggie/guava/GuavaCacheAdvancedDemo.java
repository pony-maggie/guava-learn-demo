package com.github.ponymaggie.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

//使用guava构建本地缓存--进件篇

//可以看看下面这篇文章，不使用guava模拟本地缓存的例子:
//https://blog.csdn.net/fjkxyl/article/details/72152411
public class GuavaCacheAdvancedDemo {
    private Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(10)
            .concurrencyLevel(5)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public String getCache(String key, final String threadName){

        System.out.println("getCache thread name:" + threadName);
        String value = null;

        try {
            value = cache.get(key, new Callable<String>() {
                public String call() throws Exception {
                    System.out.println("ThreadName 执行业务数据并返回处理结果的数据（访问数据库等）=============="+threadName);
                    return "dataValue";
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return value;

    }


    public static void main(String[] args) {
        final GuavaCacheAdvancedDemo demo = new GuavaCacheAdvancedDemo();

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("T1======start========");
                String value=demo.getCache("key","T1");
                System.out.println("T1 value=============="+value);
                System.out.println("T1======end========");

            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T2======start========");
                String value=demo.getCache("key","T2");
                System.out.println("T2 value=============="+value);
                System.out.println("T2======end========");

            }
        });

        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T3======start========");
                String value=demo.getCache("key","T3");
                System.out.println("T3 value=============="+value);
                System.out.println("T3======end========");

            }
        });

        t1.start();
        t2.start();
        t3.start();

    }
}
