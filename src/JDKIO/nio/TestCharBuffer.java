package JDKIO.nio;

import java.nio.CharBuffer;

public class TestCharBuffer {

	private static String[] strs = 
     {
         "A random string value",
         "The product of an infinite number of monkeys",
         "Hey hey we're the monkees",
         "Opening act for the Monkees:Jimi Hendrix",
         "Scuse me while I kiss this fly",
         "Help Me! Help Me!"
     };
	
	private static int index = 0;
	
	/**
	 * 向buffer内放置数据
	 * @param buffer
	 * @return
	 */
	private static boolean fillBuffer(CharBuffer buffer) {
		if(index >= strs.length) {
			return false;
		}
		String str = strs[index++];
		for(int i = 0;i<str.length();i++) {
			buffer.put(str.charAt(i));
		}
		return true;
	}
	
	/**
	 * 从buffer中将数据取出
	 * @param buffer
	 */
	private static void drainBuffer(CharBuffer buffer) {
		while(buffer.hasRemaining()) {
			System.out.print(buffer.get());
		}
		System.out.println("");
	}
	
	public static void main(String[] args) {
		// 每个Buffer子类都是使用allocate方法来实例化具体的子类，且实例化出来的都是Heap*Buffer
		CharBuffer cb = CharBuffer.allocate(100);
		while(fillBuffer(cb)) {
			cb.flip();
			drainBuffer(cb);
			cb.clear();
		}
	}
}
