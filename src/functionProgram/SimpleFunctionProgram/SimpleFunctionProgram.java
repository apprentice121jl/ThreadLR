package functionProgram.SimpleFunctionProgram;

/**
 * @author julong
 * @description  函数式编程，传入参数简单示例
 * @createTime 2020/7/15  14:32
 **/
public class SimpleFunctionProgram {

    public static void main(String[] args) {

        InterfaceInfo info = (int i,String num) ->{
                                                        return String.valueOf(i+Integer.valueOf(num));
                                                    };
        addCal(info,1,"2");
    }

    public static void  addCal(InterfaceInfo info,int i,String num){
        System.out.println("info = [" + info.add(i,num) + "], i = [" + i + "]");
    }
}
