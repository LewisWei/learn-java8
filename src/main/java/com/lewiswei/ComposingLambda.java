package com.lewiswei;

import java.util.Arrays;
import java.util.List;

/**
 * 组合多个 lambda 表达式
 */
public class ComposingLambda {

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1, 2, 3, 5, 4, 6, 7, 8, 9, 10);

        // 对第一个大于3的偶数进行 double
        // before java8
        System.out.println(findFirstGt3AndEvenThenDoubleIt(values));

        // java 8
        int result = values.stream()
                .filter(e -> e > 3)
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .findFirst() // 返回 Option(8)
                .orElse(0);  // 当不存在符合条件的值时，可以设置异常值
        System.out.println(result);
    }

    private static int findFirstGt3AndEvenThenDoubleIt(List<Integer> values) {
        int result = 0;
        for (Integer e : values) {
            if (e > 3 && e % 2 == 0) {
                result = e * 2;
                break;
            }
        }
        return result;
    }
}
