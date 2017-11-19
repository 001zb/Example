import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author 강호
 *	
 * @2017年11月19日 上午10:12:33
 *  
 * 功能：线程池版本，因为一个客户端对应一个线程，想要实现多个客户端与一个服务器通信就要考虑线程池了
 *
 */
public class TradtioalSocketPoolDemo {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ExecutorService threadPool=Executors.newCachedThreadPool();
		ServerSocket serversocket=new ServerSocket(2222);
		System.out.println("服务端启动");
		while(true){
			final Socket socket = serversocket.accept();
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("有新客户端连接上了...");
					InputStream stream=null;
					try {
						stream = socket.getInputStream();
					} catch (IOException e) {
						e.printStackTrace();
					}
					byte[] b=new byte[1024];
					while(true){
						int read=-1;
						try {
							read = stream.read(b);
						} catch (IOException e) {
							e.printStackTrace();
						}
						if(read==-1){
							break;
						}
						String info=null;
						try {
							info = new String(b,0,read,"GBK");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						System.out.println(info);
					}
				}
			});
		}
		
	}
}
