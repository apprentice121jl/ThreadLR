package functionProgram.SimpleFunctionProgram;

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

            System.out.println(getStr(() -> "hello wolrd"));
        }

        public static <T> T getStr(Supplier<T> supplier) {
            return supplier.get();
        }
}
