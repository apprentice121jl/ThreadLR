package atomic;

/**
 * JL
 * 2020/2/23  20:38
 **/
public class LongAddrThread implements Runnable {

    protected long startTime;
    LongAdderDemo out ;

    public LongAddrThread(LongAdderDemo out) {
        this.out = out;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        long v = out.lacount.sum();
        while(v < LongAdderDemo.TARGET_COUNT){
            out.lacount.increment();
            v = out.lacount.sum();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("SyncThread spend:"+(endTime-startTime)+ " v="+ v);
        LongAdderDemo.cdl.countDown();
    }
}
