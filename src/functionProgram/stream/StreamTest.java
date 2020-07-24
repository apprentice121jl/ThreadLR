package functionProgram.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author julong
 * @description 流测试用例
 * https://segmentfault.com/a/1190000021569339
 * 1.集合讲的是数据，流讲的是计算
 * 2.流是Java8的新成员，允许以声明性方式处理数据集合，即从支持数据处理操作的源生成的元素序列。
 * 3.数据处理操作   ：  类似于数据库的操作，以及函数式编程语言中的常用操作，如filter、 map、 reduce、 find、 match、 sort等。（见例子的list转stream）
 * 4.源   ：   提供数据的源，如集合、数组或输入/输出资源。 需要注意的是从有序集合生成流时会保留原有的顺序。由列表生成的流，其元素顺序与列表一致。（见例子的list）
 * 5.元素序列   ：  流提供了一个接口，可以访问特定元素类型的一组有序值
 *
 * 中间操作：中间操作会返回另一个流。注意：这边并不会计算，只是把流串起来。（见例子stream的创建）
 * @see TerminalTest  终端操作
 *
 **/
public class StreamTest {
    public static void main(String[] args) {
        Consumer consumer = System.out::println;
        // 取出字符串长度为1，前两个字符串
        List<String> list = Arrays.asList("1","3", "2", "3","11", "22", "33");
        Stream<String> stream = list.stream()
                .filter((str) -> str.length() == 1).limit(2);
        System.out.println("将流转换为List：  "+stream.collect(Collectors.toList()));
        // 中间操作：遍历，filter传递的是Predicate，返回boolean
        consumer.accept("遍历操作");
        list.stream().filter((String str)->str.length() == 1).forEach(System.out::println);
        //中间操作：去除重复
        consumer.accept("去除重复操作");
        list.stream().distinct().forEach(e->System.out.print(e+" "));
        // 跳过前面三个字符串
        consumer.accept("跳过前三个元素");
        list.stream().skip(3).forEach(e->System.out.print(e+" "));
        // 排序
        consumer.accept("排序");
        list.stream().sorted().forEach(e->System.out.print(e+" "));
        consumer.accept("比较器排序");
        list.stream().sorted((String a,String b)->{if(Integer.valueOf(a).intValue() > Integer.valueOf(b).intValue()){
            return 1;
        }else if(Integer.valueOf(a).intValue() < Integer.valueOf(b).intValue()){
            return -1;
        }else{
            return 0;
        }
        }).forEach(e->System.out.print(e+" "));
        // 对流的中间过程进行打印
        consumer.accept("对流的中间过程进行打印");
        Stream.of("1", "2", "3", "11", "22", "33")
                .filter((str) -> str.length() == 1)
                .peek((t) -> {
                    System.out.println(t + " " + "sorted");
                }).sorted().peek((t) -> {
                    System.out.println(t + "  " + "sorted2");
                }).limit(2).peek((t) -> {
                    System.out.println(t + "  " + "sorted3");
                }).collect(Collectors.toList()).forEach(System.out::print);

        // map是映射，接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素
        consumer.accept("map操作");
        Stream.of(1, 2, 3).map(n -> n * n).forEach(e->System.out.print(e+" "));
        // 在map映射后，得到的是Stream<String[]>的流，再通过flatMap的扁平化处理，转化为Stream<String>的流
        consumer.accept("flatMap操作");
        Stream.of(new String[]{"123", "345"})
                .map((s) -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);
    }
}
