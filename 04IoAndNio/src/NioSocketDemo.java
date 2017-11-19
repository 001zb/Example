import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author 강호
 *	
 * @2017年11月19日 上午10:44:47
 *  
 * 功能：通过NIO与客户端建立通讯连接
 *
 */
public class NioSocketDemo {
	private Selector selector;//通道选择器(管理器)

	public static void main(String[] args) {
		NioSocketDemo nio=new NioSocketDemo();
		nio.initserver(9999);
		nio.listenSelector();
	}

	private void listenSelector() {
		//轮询监听选择器 Selector
		while(true){//等待用户连接
			//select 模型 多路复用模型
			try {
				this.selector.select();//阻塞点
				System.out.println("新用户已连接");
				Iterator<SelectionKey> itkeys = this.selector.selectedKeys().iterator();
				while(itkeys.hasNext()){
					SelectionKey key = itkeys.next();
					itkeys.remove();
					//处理请求
					handler(key);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * 处理客户端请求
	 */
	private void handler(SelectionKey key) {
		SocketChannel socketChannel=null;
		ServerSocketChannel serverChannel=null;
		if(key.isAcceptable()){
			//处理客户端连接请求
			serverChannel=(ServerSocketChannel)key.channel();
			try {
				socketChannel = serverChannel.accept();
				socketChannel.configureBlocking(false);//设置非阻塞
				//接收客户端发送的信息时,需要给通讯设置读的权限
				socketChannel.register(selector, SelectionKey.OP_READ);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(key.isReadable()){
			socketChannel=(SocketChannel) key.channel();
			ByteBuffer dst=ByteBuffer.allocate(1024);
			try {
				int read = socketChannel.read(dst);
				if(read>0){
					String info=new String(dst.array(),"GBK").trim();
					System.out.println(info);
				}else{
					socketChannel.close();
					System.out.println("服务器关闭了");
					key.channel();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void initserver(int port) {
		ServerSocketChannel serverChannel=null;
		try {
			serverChannel = ServerSocketChannel.open();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			serverChannel.configureBlocking(false);//设置非阻塞，默认是阻塞
			serverChannel.socket().bind(new InetSocketAddress(port));
			this.selector = Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);//服务器端接收客户端连接事件
			System.out.println("服务器已经启动");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
