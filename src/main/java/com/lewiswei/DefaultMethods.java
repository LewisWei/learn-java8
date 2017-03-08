package com.lewiswei;

/**
 * Created by thinkpad on 2017/3/8.
 */
public class DefaultMethods {

    public static void main(String[] args) {
        System.out.println("============ default method =============");
        FlyTest flyTest = new FlyTest();
        flyTest.takeOff();
        flyTest.turn();
        flyTest.cruise();
        flyTest.land();

        System.out.println("============ interface override default method =============");
        // override default method
        FastFlyTest fastFlyTest = new FastFlyTest();
        fastFlyTest.takeOff();

        System.out.println("============ class and interface both override default method =============");
        // nearly implements
        SeaPlane seaPlane = new SeaPlane();
        seaPlane.takeOff(); // FastFly::takeOff 按照最近原则调用 default 的实现
        seaPlane.turn();    // Fly::turn
        seaPlane.cruise();  // Fly::cruise
        seaPlane.land();    // Vehicle::land 类继承体系内的方法优先于接口的 default 方法
    }
}

/**
 * default methods
 */
interface Fly {

    default void takeOff() {
        System.out.println("Fly::takeOff");
    }

    default void turn() {
        System.out.println("Fly::turn");
    }

    default void cruise() {
        System.out.println("Fly::cruise");
    }

    default void land() {
        System.out.println("Fly::lands");
    }
}

/**
 * 测试单独实现 Fly 时的方法调用
 */
class FlyTest implements Fly {
}


interface FastFly extends Fly {

    @Override
    default void takeOff() {
        System.out.println("FastFly::takeOff");
    }
}

class FastFlyTest implements FastFly {
}

class Vehicle {
    public void land() {
        System.out.println("Vehicle::land");
    }
}

class SeaPlane extends Vehicle implements FastFly, Sail {

    /**
     * 当实现的接口中有相同的 default 方法时，必须在类中对这个方法 Override
     */
    @Override
    public void cruise() {
        // 可以使用 super 关键字来调用非静态的 default 方法
        Sail.super.cruise();
    }
}

interface Sail {
    default void cruise() {
        System.out.println("Sail::cruise");
    }
}