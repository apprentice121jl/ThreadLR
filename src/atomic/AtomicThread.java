package atomic;

/**
 * JL
 * 2020/2/23  20:19
 **/
public class AtomicThread implements Runnable  {

    protected long startTime;
    LongAdderDemo out ;

    public AtomicThread(LongAdderDemo out) {
        this.startTime = System.currentTimeMillis();
        this.out = out;
    }

    @Override
    public void run() {
        long v = out.acount.get();
        while(out.acount.get() < LongAdderDemo.TARGET_COUNT){
            out.acount.incrementAndGet();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("SyncThread spend:"+(endTime-startTime)+ " v="+ out.acount.get());
        LongAdderDemo.cdl.countDown();
    }
}
