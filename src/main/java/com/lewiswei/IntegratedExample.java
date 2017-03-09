package com.lewiswei;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class IntegratedExample {

    public static void main(String[] args) {
        // 串行
        Timeit.code(() -> findImperative(ProductUtils.getProductsName()));
        // stream 串行
        Timeit.code(() -> findFunctional(ProductUtils.getProductsName().stream()));
        // parallelStream 并行
        Timeit.code(() -> findFunctional(ProductUtils.getProductsName().parallelStream()));
    }

    private static ProductInfo lowest = new ProductInfo("", 0.0);

    /**
     * java8 lambda 和 stream
     *
     * @param productsName
     */
    private static void findFunctional(Stream<String> productsName) {
        ProductInfo highPriceInLessThan50 = productsName
                .map(ProductUtils::getPrice)
                .filter(ProductUtils.isPriceLessThan(50))
                .reduce(lowest, ProductUtils::pickHigh);
        System.out.println(highPriceInLessThan50);
    }

    /**
     * 必要的执行逻辑，不适用 java8 的 stream
     *
     * @param productsName
     */
    private static void findImperative(List<String> productsName) {
        List<ProductInfo> products = new ArrayList<>();
        for (String productName : productsName) {
            products.add(ProductUtils.getPrice(productName));
        }

        List<ProductInfo> productsLessThan50 = new ArrayList<>();
        for (ProductInfo product : products) {
            if (ProductUtils.isPriceLessThan(50).test(product)) {
                productsLessThan50.add(product);
            }
        }

        ProductInfo highPriceInLessThan50 = lowest;
        for (ProductInfo product : productsLessThan50) {
            highPriceInLessThan50 = ProductUtils.pickHigh(product, highPriceInLessThan50);
        }

        System.out.println(highPriceInLessThan50);
    }


    /**
     * model
     */
    static class ProductInfo {
        private final String name;
        private final Double price;

        public ProductInfo(String name, Double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return "ProductInfo{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    static class ProductUtils {

        /**
         * 初始化测试数据
         *
         * @return
         */
        static List<String> getProductsName() {
            List<String> fakeList = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                fakeList.add("" + i);
            }
            return fakeList;
        }

        /**
         * 模拟向外部资源发起请求，每次请求耗时1秒
         *
         * @param productName
         * @return
         */
        static ProductInfo getPrice(final String productName) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new ProductInfo(productName, Double.parseDouble(productName));
        }

        /**
         * 查看商品价格是否少于指定的数值
         *
         * @param thatPrice
         * @return
         */
        static Predicate<ProductInfo> isPriceLessThan(final double thatPrice) {
            return productInfo -> productInfo.price < thatPrice;
        }

        /**
         * 获取两个商品中价格高的那个
         *
         * @param productInfo1
         * @param productInfo2
         * @return
         */
        static ProductInfo pickHigh(final ProductInfo productInfo1, final ProductInfo productInfo2) {
            return productInfo1.price > productInfo2.price ? productInfo1 : productInfo2;
        }
    }
}
