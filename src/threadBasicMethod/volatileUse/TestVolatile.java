package threadBasicMethod.volatileUse;

public class TestVolatile {

    /**
     * 只有两个线程存在时间先后顺序，volatile先行发生原则才能成功，否则失败
     * JVM P429
     *
     * @param args
     */
    public static void main(String[] args) {

        TestObject testObject = new TestObject();


        new Thread(() -> {
            int i = testObject.getI();
            System.out.println(i);
        }).start();

        new Thread(() -> {
            /* try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            testObject.setI(1);

        }).start();
    }
}
