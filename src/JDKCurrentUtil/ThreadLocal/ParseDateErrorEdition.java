package JDKCurrentUtil.ThreadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JL
 * 2020/3/15  13:58
 * SimpleDateFormat不是线程安全的,类的内部存在其他共享变量
 *
 **/
public class ParseDateErrorEdition implements Runnable {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    int i = 0;

    public ParseDateErrorEdition(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            Date t = sdf.parse("2015-03-29 19:29:" + i % 60);
            System.out.println(i+" : "+t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            es.execute(new ParseDateErrorEdition(i));
        }
    }
}
