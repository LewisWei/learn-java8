package com.lewiswei;

/**
 * Created by thinkpad on 2017/3/9.
 */
public class Timeit {

    public static void code(Runnable runner) {
        long start = System.nanoTime();
        try {
            runner.run();
        } finally {
            long end = System.nanoTime();
            System.out.println("Time taken(s):" + (end - start) / 1.0e9);
        }
    }
}
