package main;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import netty.DiscardServerHandler;
import netty.ErrorServerHandler;
import netty.ServerHandler;
import netty.ServerHandler2;

/**
 * 
 * @author admin
 *
 */
public class DiscardServer {
	private int port;
	public DiscardServer(int port){
		this.port = port;
	}
	public void run() throws Exception{
		//MultithreadEventLoopGroup implementations which is used for NIO Selector based Channels.
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();
		
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(parentGroup, childGroup)
		.channel(NioServerSocketChannel.class)//使用NioServerSocketChannel接收进来的连接
		.childHandler(new ChannelInitializer<SocketChannel>(){

			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				// TODO Auto-generated method stub
				sc.pipeline().addLast(new ServerHandler());
			}
			
		})
		.option(ChannelOption.SO_BACKLOG, 128)
		.childOption(ChannelOption.SO_KEEPALIVE, true);
		//绑定端口，开始接受进来的连接
		ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
		//等待服务器socket关闭
		//在这个例子中，这不会发生，当你可以优雅地关闭你的服务器.
		channelFuture.channel().closeFuture().sync();
	}
	
	public static void main(String[] args) {
		try{
			new DiscardServer(8080).run();
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
}
