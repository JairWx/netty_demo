package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 查看收到的数据
 * 以在命令行上输入telnet localhost 8080或者其他类型参数
 * @author admin
 *
 */
public class ServerHandler2 extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf)msg;
		try{
			//可以打印字符串
			System.out.println(in.toString(CharsetUtil.US_ASCII));
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		//super.exceptionCaught(ctx, cause);
	}
	
}
