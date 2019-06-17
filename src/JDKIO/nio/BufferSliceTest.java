package JDKIO.nio;

import java.nio.ByteBuffer;

public class BufferSliceTest {

	public static void main(String[] args) {
		// 缓冲区分片
		// SliceBufferTest();
		
		// 只读缓冲区
		readOnlyBufferTest();
	}

	public static void readOnlyBufferTest() {
		
		ByteBuffer buffer = ByteBuffer.allocate(10);
		for (int i = 0; i < buffer.capacity(); i++) {
			buffer.put((byte)i);
		}
		// 只读缓冲区:该方法返回一个与原缓冲区完全相同的缓冲区，并与原缓冲区共享数据，只不过是只读的。
		// 如果原缓冲区的内容发生了变化，只读缓冲区的内容也随之发生变化.
		// 只能把常规缓冲区转换为只读缓冲区，而不能将只读的缓冲区转换为可写的缓冲区
		ByteBuffer asReadOnlyBuffer = buffer.asReadOnlyBuffer();
		
		// 改变原缓冲区的内容
	    for (int i=0; i<buffer.capacity(); ++i) {
	        byte b = buffer.get( i );
	        b *= 10;
	        buffer.put( i, b );
	    }
		asReadOnlyBuffer.flip();
		while(asReadOnlyBuffer.remaining() > 0) {
			byte b = asReadOnlyBuffer.get();
			System.out.println(b);
		}
	}

	public static void SliceBufferTest() {
		ByteBuffer buffer = ByteBuffer.allocate(10);
		for (int i = 0; i < buffer.capacity(); i++) {
			buffer.put((byte)i);
		}
		// 在原来缓冲区的基础上，创建子缓冲区;子缓冲区和原缓冲区的数组空间是共享的
		buffer.position(3);
		buffer.limit(7);
		ByteBuffer slice = buffer.slice();
		
		for (int i = 0; i < slice.capacity(); i++) {
			byte b = slice.get(i);
			b *= 10;
			slice.put(i,b);
		}
		buffer.position(0);
		buffer.limit(buffer.capacity());
		// buffer.flip();// flip在此处不起作用
		while(buffer.remaining() > 0) {
			System.out.println(buffer.get());
		}
	}
}
