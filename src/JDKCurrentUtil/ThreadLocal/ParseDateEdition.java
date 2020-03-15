package JDKCurrentUtil.ThreadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JL
 * 2020/3/15  13:58
 * SimpleDateFormat不是线程安全的,类的内部存在其他共享变量,
 *
 **/
public class ParseDateEdition implements Runnable {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<>();
    int i = 0;

    public ParseDateEdition(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            if(tl.get() == null){
                tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
            String strTime = "2015-03-29 19:29:" + i % 60;
            String substring = strTime.substring(17);
            String substring1 = strTime.substring(0, 17);
           if(substring.length() == 1){
               substring = "0"+substring;
               substring1 = substring1 +substring;
           }else{
               substring1 = substring1 + substring;
           }

            Date t = tl.get().parse(substring1);
            String formatStr = tl.get().format(t);
            if(substring1.equals(formatStr)){
                System.out.println("相同  初始值："+substring1+"    格式化后："+formatStr);
            }else{
                System.out.println("不同  初始值："+substring1+"    格式化后："+formatStr);
            }
            System.out.println(i+" : "+t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 10; i < 1000; i++) {
            es.execute(new ParseDateEdition(i));
        }

        es.shutdown();
    }
}
