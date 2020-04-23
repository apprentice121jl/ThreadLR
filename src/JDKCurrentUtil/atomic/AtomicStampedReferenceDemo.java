package JDKCurrentUtil.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {


    static AtomicStampedReference<Integer>  money = new AtomicStampedReference<Integer>(19,0);



    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
           final int timestamp = money.getStamp();
            new Thread(() -> {
                while (true) {
                    while (true) {
                        Integer m = money.getReference();
                        if (m < 20) {
                            if (money.compareAndSet(m, m + 20,timestamp ,timestamp+ 1)) {
                                System.out.println("余额小于20元，充值成功，余额：" + money.getReference() + " 元");
                                break;
                            }
                        } else {

                            break;
                        }
                    }

                }
            }).start();
        }

            new Thread(()->{
                for (int j = 0; j < 100 ; j++) {
                    while(true){
                        int timestamp = money.getStamp();
                        Integer m = money.getReference();
                        if(m > 10){
                            System.out.println("大于10元");
                            if(money.compareAndSet(m,m-10,timestamp,timestamp + 1)){
                                System.out.println("成功消费10元，余额："+money.getReference());
                                break;
                            }
                        }else{
                                System.out.println("没有足够金额");
                                break;
                        }
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();


    }




}
