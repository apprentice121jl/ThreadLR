package threadBasicMethod.volatileUse;

public class TestVolatile {

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
