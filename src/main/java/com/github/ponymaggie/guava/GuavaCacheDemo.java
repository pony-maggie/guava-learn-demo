package com.github.ponymaggie.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

//使用guava构建本地缓存
public class GuavaCacheDemo {
    public static void main(String[] args) {

        Cache<String, String> cache = CacheBuilder.newBuilder()
                .initialCapacity(10) //为缓存设置一个合理大小初始容量
                .maximumSize(100)   //缓存最大容量,超过之后就会按照LRU最近虽少使用算法来移除缓存项
                .expireAfterWrite(5, TimeUnit.SECONDS)//缓存过期时间
                .concurrencyLevel(10)//并发级别，并发级别指的是可以同时写入缓存的线程数
                .recordStats()//统计缓存命中率
                .build();

        cache.put("key1", "value1");
        cache.put("key2", "value2");
        System.out.println(cache.getIfPresent("key1"));//缓存不存在时，getIfPresent返回null
        //等待缓存过期
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印null，因为缓存已经过期了
        System.out.println(cache.getIfPresent("key1"));

        // 获取缓存，当缓存不存在时，则通Callable进行加载并返回
        String valueNotExist = null;
        try {
            valueNotExist = cache.get("key1", new Callable<String>() {
                public String call() throws Exception {
                    return "value return by callable";
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(valueNotExist);

        //通过cacheloader自发加载缓存
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                System.out.println("缓存不存在，通过cacheloader加载缓存");
                return "value return by cacheloader";
            }
        });

        try {
            valueNotExist = loadingCache.get("key_not_exist");
            System.out.println(valueNotExist);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
