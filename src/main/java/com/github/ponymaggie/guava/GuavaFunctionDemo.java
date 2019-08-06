package com.github.ponymaggie.guava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

//guava的函数式编程
public class GuavaFunctionDemo {

    public static void main(String[] args) {

        //function
        Function<Integer, String> function = new Function<Integer, String>() {
            public String apply(Integer integer) {
                return integer.toString();
            }
        };

        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        List<String> stringList = Lists.transform(list, function); //int转换为string
        Map map = Maps.asMap(Sets.newHashSet(list), function);//键值是set中的值，value是function计算后的值
        System.out.println(map);

        //Suppliers的主要功能是创建包裹的单例对象，通过get方法可以获取对象的值
        Supplier<Integer> wrapper = Suppliers.memoize(new Supplier<Integer>() {
            public Integer get() {
                System.out.println("init object");
                return 1;
            }
        });

        System.out.println(wrapper.get());
        System.out.println(wrapper.get());//这里不会输出"init object"

        Supplier userInfo = Suppliers.memoize(new Supplier<User>() {
            public User get() {
                return new User("test");
            }
        });

        User first = (User)userInfo.get();
        User second = (User)userInfo.get();
        if(first == second){
            System.out.println("same object");
        }

        //predicate断言
        Predicate<Integer> predicate = new Predicate<Integer>() {
            public boolean apply(Integer input) {
                return input % 2 == 0;
            }
        };

        List<Integer> list2 = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);

        System.out.println(Iterables.find(list, predicate));     // 查找首个偶数，2
        System.out.println(Iterables.indexOf(list, predicate));  // 查找首个偶数的下标，1
        System.out.println(Iterables.all(list, predicate));      // 是否所有元素是否为偶数
    }
}

@Data
@AllArgsConstructor
class User{
    private String name;
}
