package functionProgram.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author julong
 * @description 终端操作
 * 终端操作：会从流的流水线生成结果。其结果是任何不是流的值，比如List、 Integer，甚至void。（见例子的输出）
 * @see StreamAndMiddleTest 中间操作
 **/
public class TerminalTest {

    public static void main(String[] args) {
        // anyMatch一个匹配就行，allMatch是全匹配，noneMatch和allMatch相反，全不匹配。
        // anyMatch、 allMatch和noneMatch这三个操作就是Java中&&和||运算符短路在流中的版本。

        // 字符串数组中是否有长度为1的字符串,有一个匹配就返回true
        boolean anyMatch = Stream.of("1", "2", "3", "11", "22", "33").anyMatch( (str) -> str.length() == 1 );
        System.out.println("anyMatch:   "+anyMatch);

        // 字符串数组中字符串长度 全都 等于2的字符串
        boolean allMatch = Stream.of("12", "22", "34", "11", "22", "33").allMatch((str) -> str.length() == 2);
        System.out.println("allMatch:   "+allMatch);
        // 字符串数组中不存在等于2的字符串
        boolean noneMatch = Stream.of("1", "2", "3", "11", "22", "33").noneMatch((str) -> str.length() == 2);
        System.out.println("noneMatch:   "+noneMatch);
        //---------------------------------华丽的分割线-------------------------------------
        // 返回第一个元素，获取第一个元素，有可能为空，返回值为Optional。
        Optional<String> first = Stream.of("1", "2", "3", "11", "22", "33").findFirst();
        System.out.println("findFirst:   "+first.get());
        // 返回任意一个元素
        Optional<String> findAny = Stream.of("1", "2", "3", "11", "22", "33").parallel().findAny();
        System.out.println("findAny:   "+findAny.get());
        //规约：reduce
        int sum = 0;
        int[] arr = new int[]{1, 2, 3, 4};
        for (int num : arr) {
            sum += num;
        }
        System.out.println("JDK1.7写法："+ sum);

        // redue第一个参数是初始值是0，后面是BinaryOperator。
        Integer reduce1 = Stream.of(1, 2, 3, 4).reduce(0, (a, b) -> a + b);
        //Integer reduce = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println("reduce1: "+reduce1);

        Optional<Integer> reduce2 = Stream.of(1, 2, 3, 4).reduce((a, b) -> a + b);
        System.out.println("reduce2: "+reduce2.get());

        // 获取最大值
        Integer max = Stream.of(1, 2, 3, 4).reduce(0, Integer::max);
        System.out.println("Integer::max 最大值： " + max);

        // 获取最大值
        Optional<Integer> max2 = Stream.of(1, 2, 3, 4).max(Integer::compareTo);
        System.out.println(max2.get());
        // 获取最小值
        Optional<Integer> min = Stream.of(1, 2, 3, 4).min(Integer::compareTo);
        System.out.println("最小值： "+ min.get());

        List<Person> list = Arrays.asList(new Person("张三", 1), new Person("李四", 1), new Person("王五", 2));
        // 流转换为List
        List<Person> toList = list.stream().collect(Collectors.toList());
        toList.add(new Person("张三", 1));
        System.out.println("流转换为list：  "+toList);
        // 流转换为Map,toConcurrentMap类似于toMap，只是返回的是ConcurrentMap
        Map<String, Person> toMap = list.stream().collect(Collectors.toMap((person) -> person.getName(), person->person));
        System.out.println("流转换为map：   "+toMap);
        // 流转换为Set
        Set<Person> toSet = list.stream().collect(Collectors.toSet());
        System.out.println("流转换为set：  "+toSet);
        System.out.println("list元素数量： "+list.stream().collect(Collectors.counting()));
        System.out.println("list元素数量： "+list.stream().count());
        // 获取到Person后再取toString的集合
        String str = list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), persons -> persons.toString()));
        System.out.println(str);
        // 先把Person映射成name，再输出集合
        List<String> collect = list.stream().collect(Collectors.mapping(person -> person.getName(), Collectors.toList()));
        System.out.println(collect);
        // 通过性别分组，返回Map数据   groupingByConcurrent类似于groupingBy，只是返回的是ConcurrentMap
        Map<Integer, List<Person>> groupingBy = list.stream().collect(Collectors.groupingBy(Person::getSex));
        System.out.println("通过性别分组： "+groupingBy);
        // 通过性别分区
        Map<Boolean, List<Person>> partitioningBy = list.stream().collect(Collectors.partitioningBy(person -> 1 == person.getSex()));
        System.out.println("通过性别分区： "+partitioningBy);


        // 最大值
        Optional<String> maxBy = Arrays.stream(new String[]{"a", "c", "d"}).collect(Collectors.maxBy(String::compareToIgnoreCase));
        System.out.println(maxBy.get());

        // 最小值
        Optional<String> minBy = Arrays.stream(new String[]{"a", "c", "d"}).collect(Collectors.minBy(String::compareToIgnoreCase));
        System.out.println(minBy.get());

        // 求和
        int total = Arrays.stream(new Integer[]{1, 2, 3, 4}).collect(Collectors.summingInt(i -> i));
        System.out.println("求和total：" + total);
        //summingDouble、summingLong类似于summingInt

        // 求平均值
        double avg = Arrays.stream(new Integer[]{1, 2, 3}).collect(Collectors.averagingInt(i -> i));
        System.out.println("平均值：  "+ avg);
        //averagingDouble、averagingLong类似于averagingInt

        // 获取集合 元素数量 、 元素和 、元素最小值 、 平均值 、 元素最大值
        IntSummaryStatistics intSummaryStatistics = Arrays.stream(new Integer[]{1, 2, 3}).collect(Collectors.summarizingInt(i -> i));
        System.out.println(intSummaryStatistics);

        // 链接字符串
        String joining1 = Arrays.stream(new String[]{"a", "c", "d"}).collect(Collectors.joining());
        System.out.println(joining1);
        // 链接字符串的分隔符
        String joining2 = Arrays.stream(new String[]{"a", "c", "d"}).collect(Collectors.joining(","));
        System.out.println(joining2);
        // 链接字符串的分隔符、前缀、后缀
        String joining3 = Arrays.stream(new String[]{"a", "c", "d"}).collect(Collectors.joining(",", "prefix", "suffix"));
        System.out.println(joining3);

        // reducing执行结果
        Integer reducing1 = Stream.of(1, 2, 3, 4).collect(Collectors.reducing(0, (a, b) -> a + b));
        System.out.println("reducing结果： "+reducing1);
        Optional<Integer> reducing2 = Stream.of(1, 2, 3, 4).collect(Collectors.reducing((a, b) -> a + b));
        System.out.println("reducing结果： "+reducing2.get());
        Integer reducing3 = Stream.of(1, 2, 3, 4).collect(Collectors.reducing(0, i -> i * i, (a, b) -> a + b));
        System.out.println("reducing结果： "+reducing3);
    }
}
