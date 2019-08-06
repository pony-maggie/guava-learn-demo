package com.github.ponymaggie.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.Arrays;

//字符串相关
public class GuavaStringDemo {
    public static void main(String[] args) {

        //joiner
        String join = Joiner.on(":").skipNulls().join(Lists.newArrayList(1, 2, 3, null, 4));
        System.out.println(join);
        String join2 = Joiner.on(":").useForNull("0").join(Lists.newArrayList(1, 2, 3, null, 4));
        System.out.println(join2);

        //splitter比jdk自带的split好用很多
        String strSplit = ";a;b;; c;;d;";
        System.out.println(Arrays.toString(strSplit.split(";")));
        String result = Splitter.on(";").trimResults().omitEmptyStrings().split(strSplit).toString();
        System.out.println(result);

        //charmatcher
        String sequence = "this test. 1.2 !!! aaaa";
        System.out.println(CharMatcher.anyOf("12").retainFrom(sequence));//枚举匹配字符
        System.out.println(CharMatcher.is('t').retainFrom(sequence));//给定单一字符匹配
        System.out.println(CharMatcher.inRange('a', 'e').retainFrom(sequence));//给定字符范围匹配
        System.out.println(CharMatcher.ANY.removeFrom(sequence));//移除所有字符
        System.out.println(CharMatcher.BREAKING_WHITESPACE.replaceFrom(sequence,""));//去掉空格





    }
}
