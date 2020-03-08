package atomic;

import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * JL
 * 2020/2/23  20:54
 **/
public class LongAccumulatorTest {

    public static void main(String[] args) throws InterruptedException {
        LongAccumulator accumulator = new LongAccumulator(Long::max,Long.MIN_VALUE);
        Thread[] ts = new Thread[1000];
        for (int i = 0; i < 1000 ; i++) {
            ts[i] = new Thread(()->{
                Random random = new Random();
                long value = random.nextLong();
                accumulator.accumulate(value);
            });
            ts[i].start();
        }
        for (int j = 0; j < 1000 ; j++) {
            ts[j].join();
        }
        System.out.println(accumulator.longValue());
    }
}
