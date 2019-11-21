package JDKCurrentUtil.ThreadLocal;

public class TestThreadLocalNum {


	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {

		 protected Integer initialValue() {// 如果ThreadLocal在不设置值的情况下，默认initialValue()返回null
			   return 0;
		   }
	};
	
	public int getNextNum() {
		seqNum.set(seqNum.get()+1);
		return seqNum.get();
	}

	public static void main(String[] args) {
		TestThreadLocalNum sn = new TestThreadLocalNum();
		// 三个线程对象共享sn
		// 每个线程都会创建ThreadLocalMa对象，值存放在ThreadLocalMap中，sn只是作为一个key
		TestClient t1 = new TestClient(sn);
		TestClient t2 = new TestClient(sn);
		TestClient t3 = new TestClient(sn);
		t1.start();
		t2.start();
		t3.start();
	}
	
	
	private static class TestClient extends Thread{
		
		private TestThreadLocalNum sn ;
		
		public TestClient(TestThreadLocalNum sn) {
			this.sn = sn;
		}
		
		public void run() {
			for(int i = 0; i < 3;i++) {
				System.out.println("thread["+Thread.currentThread().getName()+ "]-->sn["+sn.getNextNum()+"]");
			}
		}
	}

}
