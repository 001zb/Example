import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author 강호
 *	
 * @2017年11月19日 上午9:34:24
 *  
 * 功能：传统socket
 * 在cmd用客户端进行连接telnet localhost 7777
 *
 */
public class TraditionalSocketDemo {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket=new ServerSocket(3333);
		System.out.println("服务端启动");
		while(true){
			Socket socket=serverSocket.accept();
			System.out.println("有新客户端连接上来了...");
			InputStream stream = socket.getInputStream();
			byte[] b=new byte[1024];
			while(true){
				int read = stream.read(b);
				if(read==-1){
					break;
				}
				String info=new String(b,0,read,"GBK");
				System.out.println(info);
			}
		}
	}
}
