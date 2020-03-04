package commonsense;

public class CPUnum {
    public static void main(String[] args) {
        // 获取CPU的数量
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("cpu num:"+i);
    }
}
