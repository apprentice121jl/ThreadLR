package functionProgram.SimpleFunctionProgram;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author julong
 * @description  Function接口简单使用
 * @createTime 2020/7/15  17:38
 **/
public class FunctionDemo {

        public static void main(String[] args) {
            List<String> list = Arrays.asList("1", "2", "3", "11", "22", "33");
            // Function接口:  R apply(T t);  R代表返回值，T代表参数
            getLength(list, (t) -> {return t.length();});
        }

        public static <T, R> void getLength(List<T> list, Function<T, R> function) {
            Map<T, R> map = new HashMap<>();
            for (T t : list) {
                map.put(t, function.apply(t));
            }
            System.out.println(map);
        }
}
