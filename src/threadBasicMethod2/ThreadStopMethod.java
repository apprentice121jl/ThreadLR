package threadBasicMethod2;

/**
 * JL
 * 2019/5/10  22:36
 * Thread#stop方法直接终止线程，并会立即释放这个线程持有的锁
 **/
public class ThreadStopMethod {
    public static User u = new User();
    public static class User{

        public User(){
            id = 0;
            name = "0";
        }

        private int id;
        private String name;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class ChangeObjectThread extends Thread{
        public  void run(){
            while(true){
                synchronized (u){
                    int v = (int) (System.currentTimeMillis()/1000L);
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }

        }
    }

    public static class ReadObjectThread extends Thread{
        public  void run(){
            while(true){
                synchronized (u){
                    if(u.getId() != Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while(true){
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stop();
        }
    }

}
