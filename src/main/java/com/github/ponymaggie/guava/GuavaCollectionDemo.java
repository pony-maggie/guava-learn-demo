package com.github.ponymaggie.guava;

import com.google.common.collect.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GuavaCollectionDemo {

    public static void main(String[] args) {

        //集合创建和初始化
        Map<String, String> map = Maps.newHashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        Map<String, String> map2 = Maps.newHashMap(map);
        System.out.println(map2.get("key1"));
        List<String> list = Lists.newArrayList("apple", "orange", "banana");

        //multimap，key可以重复，一般用来根据key获取一个list或者set
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("fruit", "apple");
        multimap.put("fruit", "orange");
        multimap.put("pet", "dog");
        multimap.put("pet", "cat");
        Collection<String> fruits = multimap.get("fruit");
        System.out.println(multimap.size());
        System.out.println(multimap.keys());
        System.out.println(fruits.toString());

        //multiset，存储重复的元素，并且可以统计
        Multiset<Integer> multiset = HashMultiset.create();
        multiset.add(1);
        multiset.add(3);
        multiset.add(3);
        multiset.add(4);
        System.out.println(multiset.size());
        System.out.println(multiset.count(3));

        //BiMap，key不重复，value也不重复，可以通过key索引value，也可以通过value索引key
        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1, "one");
        biMap.put(2, "two");
        System.out.println("value:" + biMap.inverse().get("two"));

        //ordering 排序类，某些情况下比comparator更简洁
        List<Integer> numbers = Lists.newArrayList(15, 33, 2, 90, 35);
        System.out.println(Ordering.natural().sortedCopy(numbers));
        System.out.println(Ordering.natural().reverse().sortedCopy(numbers));
        System.out.println(Ordering.natural().min(numbers));

        List<Person> personList = Lists.newArrayList(
          new Person("tom", 0, 28),
                new Person("lily", 1, 25),
                new Person("mike", 0, 35)
        );
        Ordering<Person> byAge = new Ordering<Person>() {
            @Override
            public int compare(Person left, Person right) {
                return right.getAge() - left.getAge(); //从大到小排序
            }
        };

        for(Person p:byAge.immutableSortedCopy(personList)){
            System.out.println(p);
        }

    }
}

class Person{
    String name;
    int sex;
    int age;

    public Person(String name, int sex, int age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }
}
