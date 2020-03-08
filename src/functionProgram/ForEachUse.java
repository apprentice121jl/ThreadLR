package functionProgram;

import java.util.Arrays;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * JL
 * 2020/2/22  19:42
 **/
public class ForEachUse {
    static int[] arr = {1,2,3,4,5,6,7,8};

    public static void main(String[] args) {
        noChange();
        // 遍历数组
        traversalArray();
        // 先后输出
        beforeAfterOut();
        // 统计质数个数
        countPrime();


    }

    // 在函数式编程中，所有传递的对象都不会轻易修改
    private static void noChange() {
        Arrays.stream(arr).map((x)->x = x+1).forEach(System.out::println);
        Arrays.stream(arr).forEach(System.out::println);
    }

    // 统计质数个数
    private static void countPrime() {
        long startTime = System.currentTimeMillis();
        // 串行统计
        // long count = IntStream.range(1, 1000000).filter(ForEachUse::isPrime).count();
        // 并行统计,parallel()方法得到一个并行流
        long count = IntStream.range(1,1000000).parallel().filter(ForEachUse::isPrime).count();
        long endTime = System.currentTimeMillis();
        System.out.println("质数个数："+count+"  ,用时："+ (endTime - startTime));
    }

    // 判断是否为质数
    public static boolean isPrime(int number){
        int tmp = number;
        if(tmp < 2) return false;
        for(int i = 2;Math.sqrt(tmp) >= i;i++){
            if(tmp % i == 0) return false;
        }
        return true;
    }

    private static void beforeAfterOut() {
        // 标准输出
        IntConsumer outPrintln = System.out::print;
        // 标准错误输出
        IntConsumer errprintln = System.err::print;
        Arrays.stream(arr).forEach(outPrintln.andThen(errprintln));
    }


    /**
     * 遍历数组
     */
    private static void traversalArray() {
        // Arrays.stream():返回一个流对象,是一个对象的集合，将给予我们遍历处理流内元素的功能
        Arrays.stream(arr).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.print(value+"  ");
            }
        });

        // 遍历的进阶版1
        Arrays.stream(arr).forEach((final int x )->{
            System.out.print(x+"  ");
        });

        // 遍历的进阶版2：lambda表达式左边表示参数，右边表示实现体；lambda表达式是匿名对象实现的一种新的方式
        Arrays.stream(arr).forEach(( x )->{
            System.out.print(x+"  ");
        });

        // 遍历的进阶版3：使用方法引用
        Arrays.stream(arr).forEach(System.out::print);
    }
}
