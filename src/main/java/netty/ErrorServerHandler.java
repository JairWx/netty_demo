package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class ErrorServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	/**
	 * 如果try{}后Exception则exceptionCaught获取不了异常
	 */
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf)msg;
		try{
			int i = Integer.valueOf(in.toString(CharsetUtil.US_ASCII));
			System.out.println(30/i);
		}finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	/**
	 * 通常在遇到不同的异常情况下会实现不同的处理方法
	 */
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("异常");
		cause.printStackTrace();
		ctx.write("ssss");
		ctx.close();
	}
	
}
