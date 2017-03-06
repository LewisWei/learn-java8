package com.lewiswei;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 循环遍历的演化过程
 */
public class Loop {

    public static void main(String[] args) {

        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        // before java 5
        for (int i = 0; i < values.size(); i++) {
            System.out.println(values.get(i));
        }

        // java 5  - External Loop
        for (Integer v : values) {
            System.out.println(v);
        }

        // java 8 forEach With java 6 Anonymous Inner Class
        values.forEach(new Consumer<Integer>() {
            public void accept(Integer value) {
                System.out.println(value);
            }
        });

        // java 8 lambda -> 不会生成 Loop$1.class 内部类，lambda 表达式是在字节码层面实现的特性(指令：invokedynamic)
        values.forEach((Integer value) -> System.out.println(value));

        values.forEach(value -> System.out.println(value));

        values.forEach(System.out::println);

    }
}
