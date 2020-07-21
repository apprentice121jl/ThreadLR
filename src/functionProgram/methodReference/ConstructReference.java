package functionProgram.methodReference;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author julong
 * @description TODO
 * @createTime 2020/7/21  15:41
 **/
public class ConstructReference {

    Integer value ;

    public ConstructReference(Integer value) {
        this.value = value;
    }

    public ConstructReference() {}

    @Override
    public String toString() {
        return "ConstructReference{" +
                "value=" + value +
                '}';
    }

    public static void main(String[] args) {
        // 无参创建对象
        // 方式1：
        // Supplier<ConstructReference> obj2 = ()->new ConstructReference();
        Supplier<ConstructReference> obj2 = ()->{return new ConstructReference();};
        System.out.println(obj2.get());

        //方式2：
        Supplier<ConstructReference> supplier2 = ConstructReference::new;
        System.out.println(supplier2.get());

        // 有参创建对象
        // 方式1
        Function<Integer, ConstructReference> function2= (Integer num) -> new ConstructReference(num);
        ConstructReference apply1 = function2.apply(4);
        System.out.println(apply1);
        // 方式2
        Function<Integer, ConstructReference> function3 = ConstructReference::new;
        System.out.println(function3.apply(45));

    }
}
