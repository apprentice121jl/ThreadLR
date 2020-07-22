package functionProgram.SimpleFunctionProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author julong
 * @description 一个涉及类型T的布尔表达式时，可以使用Predicate接口
 *
 * @see Predicate
 *  default Predicate<T> or(Predicate<? super T> other) {
 *         Objects.requireNonNull(other);
 *         // 整个语句为test实现
 *         return (t) -> test(t) || other.test(t);
 *     }
 **/
public class PredicateDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "11", "22", "33");
        Predicate<String> predicate = (String str) -> str.length() == 1;
        // 过滤长度为1的字符串
        filter(list, predicate);
        // 过滤长度非1的字符串
        negateFilter(list, predicate);
        // 获取相同的数据
        equalFilter(list);

        Predicate<String> orPredicate = str -> "11".equals(str);
        orFilter(list, predicate, orPredicate);

        Predicate<String> andPredicate = (String str) -> "1".equals(str);
        andFilter(list, predicate, andPredicate);
        // 相当于 （predicate && andPredicate）|| orPredicate
        andOrFilter(list, predicate, andPredicate, orPredicate);
        // 相当于  （predicate || orPredicate）&& andPredicate
        orAndFilter(list, predicate, andPredicate, orPredicate);
    }

    /**
     * 判断是否符合
     * @param list
     * @param predicate
     * @param <T>
     */
    public static <T> void filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        System.out.println(result);
    }

    public static <T> void negateFilter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.negate().test(t)) {
                result.add(t);
            }
        }
        System.out.println(result);
    }

    public static <T> void orFilter(List<T> list, Predicate<T> predicate, Predicate<T> orPredicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.or(orPredicate).test(t)) {
                result.add(t);
            }
        }
        System.out.println("orFilter:"+result);
    }

    public static <T> void andFilter(List<T> list, Predicate<T> predicate, Predicate<T> andPredicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.and(andPredicate).test(t)) {
                result.add(t);
            }
        }
        System.out.println(result);
    }

    public static <T> void equalFilter(List<T> list) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (Predicate.isEqual("1").test(t)) {
                result.add(t);
            }
        }
        System.out.println(result);
    }

    public static <T> void andOrFilter(List<T> list, Predicate<T> predicate, Predicate<T> andPredicate, Predicate<T> orPredicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.and(andPredicate).or(orPredicate).test(t)) {
                result.add(t);
            }
        }
        System.out.println(result);
    }

    public static <T> void orAndFilter(List<T> list, Predicate<T> predicate, Predicate<T> andPredicate, Predicate<T> orPredicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.or(orPredicate).and(andPredicate).test(t)) {
                result.add(t);
            }
        }
        System.out.println(result);
    }



}
