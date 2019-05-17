package JDKCurrentUtil.ThreadLocal;

/**
 * 1.通过ThreadLocal创建的副本是存储在每个Thread自己的threadLocals变量中的
 * 2.threadLocals的类型 ThreadLocal.ThreadLocalMap的key为ThreadLocal对象，因为每个线程中可有多个threadLocal变量，就像下面代码中的longLocal和stringLocal
 * 3.在进行get之前，必须先set，否则会报空指针异常。 如果想在get之前不需要调用set就能正常访问的话，必须重写initialValue()方法
 * 
 */
public class TestThreadLocal {
	
   ThreadLocal<Long>  longLocal = new ThreadLocal<Long>() {
	   protected Long initialValue() {// 如果ThreadLocal在不设置值的情况下，默认initialValue()返回null
		   return Thread.currentThread().getId();
	   }
   };
   ThreadLocal<String>  stringLocal = new ThreadLocal<String>() {
	   protected String initialValue() {
		   return Thread.currentThread().getName();
	   }
   };
   
   public void set() {
	   // set是设置到当前运行线程的ThreadLocal.ThreadLocalMap中，并且以longLocal或者stringLocal作为key
	   longLocal.set(Thread.currentThread().getId());
	   stringLocal.set(Thread.currentThread().getName());
   }
   
   public long getLong() {
	   return longLocal.get();
   }
   
   public String getString() {
	   return stringLocal.get();
   }
   
   public static void main(String[] args) throws Exception {
	
	   final TestThreadLocal test = new TestThreadLocal();
	   // test.set();
	   System.out.println(test.getLong());
	   System.out.println(test.getString());
	   
	   Thread thread1 = new Thread() {
		   public void run() {
			   // test.set();
			   System.out.println(test.getLong());
			   System.out.println(test.getString());
		   }
	   };
	   
	   thread1.start();
	   thread1.join();
	   System.out.println(test.getLong());
	   System.out.println(test.getString());
   }

}
