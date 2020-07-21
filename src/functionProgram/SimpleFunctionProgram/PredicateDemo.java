package functionProgram.SimpleFunctionProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author julong
 * @description 一个涉及类型T的布尔表达式时，就可以使用Predicate接口
 * @createTime 2020/7/15  15:35
 **/
public class PredicateDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "11", "22", "33");
        Predicate<String> predicate = (String str) -> str.length() == 1;
        filter(list, predicate);
        negateFilter(list, predicate);
        equalFilter(list);
        Predicate<String> orPredicate = (String str) -> "11".equals(str);
        orFilter(list, predicate, orPredicate);
        Predicate<String> andPredicate = (String str) -> "1".equals(str);
        andFilter(list, predicate, andPredicate);
        andOrFilter(list, predicate, andPredicate, orPredicate);
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
        System.out.println(result);
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
