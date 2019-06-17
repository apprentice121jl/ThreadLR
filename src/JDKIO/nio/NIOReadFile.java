package JDKIO.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class NIOReadFile {

	/**
	 * 1.从FileInputStream获取Channel
	 * 2.创建buffer
	 * 3.将数据从Channel读取到Buffer中
	 */
	
	public static void main(String[] args) throws IOException {
		FileInputStream fin = new FileInputStream(new File("d:\\user\\01215203\\desktop\\报文.txt"));
		
		// 获取通道
		FileChannel fc = fin.getChannel();
		// 创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		// 读取数据到缓冲区
		fc.read(buffer);
		buffer.flip();
		while(buffer.remaining()>0) {
			byte b = buffer.get();
			System.out.print((char)b);
		}
		fin.close();
		
	}
}
