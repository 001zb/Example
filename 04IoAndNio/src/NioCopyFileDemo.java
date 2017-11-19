import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 
 * @author 강호
 *	
 * @2017年11月19日 上午11:27:03
 *  
 * 功能：使用NIO复制文件
 *
 */
public class NioCopyFileDemo {
	public static void main(String[] args) {
		String inFile="G:/one.txt";
		String outFile="G:/two.txt";
		try {
			//获取源文件和目标文件的输入输出流
			FileInputStream input=new FileInputStream(inFile);
			FileOutputStream output =new FileOutputStream(outFile);
			//获取输入输出通道
			FileChannel InChannel = input.getChannel();
			FileChannel OutChannel = output.getChannel();
			//创建缓存区
			ByteBuffer buffer = ByteBuffer.allocate(1024); 
			while(true){
				//清空缓存区，使它可以接收数据
				buffer.clear();
				//将输入通道中的数据读取到缓存区
				int read = InChannel.read(buffer);
				//read方法返回读取的字节数，可能为0，如果该通道已达到流的末尾，则返回-1
				if(read==-1){
					break;
				}
				//filp方法让缓存区可以将新读入的数据写入另一个通道
				buffer.flip();
				OutChannel.write(buffer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
