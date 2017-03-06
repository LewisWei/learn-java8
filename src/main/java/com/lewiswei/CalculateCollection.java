package com.lewiswei;

import java.util.Arrays;
import java.util.List;

/**
 * 集合计算
 */
public class CalculateCollection {

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // 获得集合中所有元素乘以2的和
        // before java 8
        int total = 0;
        for (Integer e : values) {
            total += e * 2;
        }
        System.out.println(total);


        // java8
        System.out.println(
                values.stream() // 创建一个 sequential
                        .map(e -> e * 2) // 实现 Consumer
                        .reduce(0, (lastCalculateResult, e) -> lastCalculateResult + e) // 实现 BiFunction
        );
    }
}
