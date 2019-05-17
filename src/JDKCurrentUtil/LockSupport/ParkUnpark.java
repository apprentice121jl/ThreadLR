package JDKCurrentUtil.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * JL
 * 2019/5/14  23:32
 **/
public class ParkUnpark {

    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        LockSupport.unpark(t);
        LockSupport.unpark(t);
        LockSupport.park();
        System.out.println("1");
        LockSupport.park();
        System.out.println("2");

    }
}
