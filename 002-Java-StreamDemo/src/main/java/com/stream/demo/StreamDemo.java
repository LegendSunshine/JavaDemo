package com.stream.demo;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 类名称:StreamDemo
 * 类描述: stream 流操作
 *
 * @author legend
 * @since 2023/7/8
 */
public class StreamDemo {

    /**
     * 对stream进行optional 操作
     * 对null 值进行处理
     */

    public static void streamOptional(String variate) {
        Optional<String> str = Optional.ofNullable(variate);
        BigDecimal decimal = new BigDecimal("2.33");

        /* ifPresent是Java 8中的一个非常实用的函数，可以帮助我们优雅地处理可能为空的对象。
         * 具体来说，它可以判断对象是否为null，如果不为null，就执行指定的操作。*/
        str.map(String::valueOf).ifPresent(s -> System.out.println("结果:"+decimal.multiply(new BigDecimal(s))));
    }

    /*
     * 使用stream进行分组
     */

    public static void streamGroup(List<String> strList){

        Map<Integer, List<String>> listMap = strList.stream().collect(Collectors.groupingBy(String::length));

        System.out.println(listMap);

    }

    public static void main(String[] args) {
        String str = "23.66";
        streamOptional(str);
        streamOptional(null);

        List<String> strList  = new ArrayList<>();
        strList.add("apple");
        strList.add("aboard");
        strList.add("brand");
        strList.add("car");
        streamGroup(strList);
    }
}
