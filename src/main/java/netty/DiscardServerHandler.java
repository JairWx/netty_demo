package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
/**
 * Discard服务器所需Handler
 * 为了实现DISCARD协议，处理器不得不忽略所有接收到的消息
 * Handler是由Netty生成用来处理I/O事件的
 * @author admin
 *
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{

	/**
	 * ByteBuf是一个引用计数对象，这个对象必须显式地调用release()方法来释放。
	 * 注意处理器的职责是释放所有传递到处理器的引用计数对象。
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		//super.channelRead(ctx, msg);
		((ByteBuf)msg).release();
	}

	@Override
	/**
	 * 在出现Throwable对象，即当Netty由于IO错误或者处理器在处理事件抛出异常时，exceptionCaught()事件处理方法会被调用。
	 */
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		//super.exceptionCaught(ctx, cause);
	}
	
}
