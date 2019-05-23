package JDKCurrentUtil.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * 验证LockSupport非可重入锁，多次unpark不可对应多次park
 *
 **/
public class UnparkPark2 {

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
