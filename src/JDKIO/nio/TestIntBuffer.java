package JDKIO.nio;

import java.nio.IntBuffer;

public class TestIntBuffer {

	public static void main(String[] args) {
		// 分配新的int缓冲区,参数为缓冲区容量
		IntBuffer buffer = IntBuffer.allocate(8);
		for (int i = 0; i < buffer.capacity(); i++) {
			int j = 2*(i+1);
			// 将给定整数写入此缓冲区的当前位置，当前位置递增
			buffer.put(j);
		}
		// 设置缓冲区的输出索引范围：将缓冲区的position设置为0，并将缓冲区的limit设置为已有赋值的当前位置
		buffer.flip();
		// 查看初始位置和上限位置之间是否有元素
		while(buffer.hasRemaining()) {
			int j = buffer.get();
			System.out.print(j+"  ");
		}
	}
}
