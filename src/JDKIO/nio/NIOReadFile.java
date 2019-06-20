package JDKIO.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class NIOReadFile {

	/**
	 * 1.从FileInputStream获取Channel
	 * 2.创建buffer
	 * 3.将数据从Channel读取到Buffer中
	 */
	
	public static void main(String[] args) throws IOException {
		// 通道读取数据
		// channelReadTest();
		// 通道写数据
		channelWriteTest();
	}

	private static void channelWriteTest() throws FileNotFoundException, IOException {
		RandomAccessFile raf = new RandomAccessFile(new File("d:\\user\\01215203\\desktop\\报文.txt"),"rw");
		// 获取通道
		FileChannel fc = raf.getChannel();
		// 创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(35);
		String str ="666666777";
		buffer.put(str.getBytes());
		buffer.flip();
		fc.write(buffer);
		buffer.clear();
		fc.close();
	}

	private static void channelReadTest() throws FileNotFoundException, IOException {
		FileInputStream fin = new FileInputStream(new File("d:\\user\\01215203\\desktop\\报文.txt"));
		
		// 获取通道
		FileChannel fc = fin.getChannel();
		// 创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(35);
		// 使用文件通道读取数据到缓冲区
		fc.read(buffer);
		buffer.flip();
		while(buffer.remaining()>0) {
			byte b = buffer.get();
			System.out.print((char)b);
		}
		buffer.clear();
		fin.close();
	}
}
