package functionProgram.SimpleFunctionProgram;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * @author julong
 * @description Supplier接口简单示例
 * @createTime 2020/7/15  17:30
 **/
public class SupplierDemo {
        public static void main(String[] args) {
            // 不接受此种写法
            // System.out.println((() -> "hello wolrd").get());
            Supplier<String> supplier = () -> "hello wolrd";
            String s = supplier.get();

            System.out.println(getStr(() -> "hello wolrd"));

            String str = "hello wolrd";
            // 第一个方法
            System.out.println(getStrLength(() -> str.length()));
            // 第二个方法:调用的外部对象str的方法
            System.out.println(getStrLength(str::length));
        }

        public static <T> T getStrLength(Supplier<T> supplier) {
            return supplier.get();
        }

        public static <T> T getStr(Supplier<T> supplier) {
            return supplier.get();
        }
}
