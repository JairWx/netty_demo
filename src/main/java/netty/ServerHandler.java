package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf)msg;
		try{
			while (in.isReadable()) {
				/**
				 * 如果在前面调用 System.out.print(in.readByte()) 会报IndexOutOfBoundsException错误
				 * java.lang.IndexOutOfBoundsException: readerIndex(3) + length(1) exceeds writerIndex(3): 
				 * PooledUnsafeDirectByteBuf(ridx: 3, widx: 3, cap: 1024)
				 */
				System.out.print((char)in.readByte());//输出字符
				System.out.flush();//flush();是流式输入输出常用的一个方法，表示强制请求清空缓冲区，让i/o系统立马完成它应该完成的输入、输出动作。
			}
		}finally {
			ReferenceCountUtil.release(msg);//本质与((ByteBuf)msg).release()相同，强转msg为ReferenceCounted类
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("异常是:");
		cause.printStackTrace();
		//super.exceptionCaught(ctx, cause);
	}
	
}
