package functionProgram.SimpleFunctionProgram;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author julong
 * @description 如果需要访问类型T的对象，并对其执行某些操作，就可以使用这个Consumer接口。
 * @createTime 2020/7/15  17:11
 **/
public class ConsumerDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "11", "22", "33");
        Consumer<String> consumer = (String str) -> System.out.print("first "+ str+"  ");
        forEach(list, consumer);

        Consumer<String> andThenConsumer = (String str) -> System.out.print("andThen:" + str+"  ");
        forEach(list, consumer, andThenConsumer);

        Consumer<String> andThen2Consumer = (String str) -> System.out.print("andThen2:" + str+" ");
        forEach(list, consumer, andThenConsumer, andThen2Consumer);

    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer, Consumer<T> andThenConsumer) {
        for (T t : list) {
            consumer.andThen(andThenConsumer).accept(t);
        }
    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer, Consumer<T> andThenConsumer, Consumer<T> andThen2Consumer) {
        for (T t : list) {
            consumer.andThen(andThenConsumer).andThen(andThen2Consumer).accept(t);
        }
    }

}
