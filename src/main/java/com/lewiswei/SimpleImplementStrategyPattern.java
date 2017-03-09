package com.lewiswei;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 使用 Predicate 实现策略模式
 */
public class SimpleImplementStrategyPattern {

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6);

        System.out.println("======before java 8========");
        // copy and paste
        System.out.println(totalValues(values));
        System.out.println(totalEvenValues(values));
        System.out.println(totalOddValues(values));

        System.out.println("======java 8========");
        // java8 lambda predicate
        System.out.println(totalValues(values, e -> true));
        System.out.println(totalValues(values, e -> e % 2 == 0));
        System.out.println(totalValues(values, e -> e % 2 != 0));
    }

    /**
     * 使用 Predicate 完成过滤条件
     *
     * @param values
     * @param selector
     * @return
     */
    private static int totalValues(List<Integer> values, Predicate<Integer> selector) {
        return values.stream()
                .filter(selector)
                .reduce(0, (c, e) -> c + e);
    }

    private static int totalOddValues(List<Integer> values) {
        int total = 0;
        for (Integer e : values) {
            if (e % 2 != 0) total = total + e;
        }
        return total;
    }

    private static int totalEvenValues(List<Integer> values) {
        int total = 0;
        for (Integer e : values) {
            if (e % 2 == 0) total = total + e;
        }
        return total;
    }


    private static int totalValues(List<Integer> values) {
        int total = 0;
        for (Integer e : values) {
            total = total + e;
        }
        return total;
    }
}
