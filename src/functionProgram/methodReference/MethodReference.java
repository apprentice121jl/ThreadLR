package functionProgram.methodReference;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author julong
 * @description 静态方法的引用
 * @createTime 2020/7/21  14:53
 **/
public class MethodReference {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("1", "2", "3", "4");
        /* 方法1 */
        sum(list, (String str) -> Integer.valueOf(str));
        // 方法2   方法引用：相当于实现Function接口，引用的是类的静态方法
        sum(list, Integer::parseInt);
    }

    public static <T, R> void sum(List<T> list, Function<T, R> function) {
        Integer sum = 0;
        for (T t : list) {
            sum += (Integer) function.apply(t);
        }
        System.out.println(sum);
    }
}
