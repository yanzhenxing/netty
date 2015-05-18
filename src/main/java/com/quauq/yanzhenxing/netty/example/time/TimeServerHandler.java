package com.quauq.yanzhenxing.netty.example.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler  extends ChannelHandlerAdapter {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		final ByteBuf time=ctx.alloc().buffer(4);
		time.writeInt((int)(System.currentTimeMillis() / 1000L + 2208988800L));
		final ChannelFuture future=ctx.writeAndFlush(time);
		future.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture f) throws Exception {
				// TODO Auto-generated method stub
				assert f==future;
				ctx.close();
			}
		});
		super.channelActive(ctx);
	}

	
}
