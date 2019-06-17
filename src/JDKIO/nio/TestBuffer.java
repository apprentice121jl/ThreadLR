package JDKIO.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestBuffer {

	public static void main(String[] args) throws IOException {
		FileInputStream fin = new FileInputStream(new File("d:\\user\\01215203\\desktop\\报文.txt"));
		FileChannel fc = fin.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(10);
		outPut("初始化         ", buffer);
		fc.read(buffer);
		outPut("call read", buffer);
		buffer.flip();
		outPut("call flip", buffer);
		while(buffer.remaining() > 0) {
			byte b = buffer.get();
		}
		outPut("call get()",buffer);
		buffer.clear();
		outPut("call clear()",buffer);
		fin.close();
	}
	
	
	public static void outPut(String step ,Buffer buffer) {
		System.out.print(step+" : ");
		System.out.print("capacity: "+buffer.capacity()+", ");
		System.out.print("position: " + buffer.position() +", ");
		System.out.print("limit: " + buffer.limit() +", ");
		System.out.println();
	}
}
