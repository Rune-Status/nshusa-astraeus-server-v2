package io.astraeus.net.channel;

import io.astraeus.net.codec.fs.FileServerDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * A {@link ChannelInitializer} used for handling a channel's tasks.
 * @author Professor Oak
 */
public final class PipelineFactory extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		final ChannelPipeline pipeline = channel.pipeline();
				
		pipeline.addLast("decoder", new FileServerDecoder());
		pipeline.addLast("timeout", new IdleStateHandler(15, 0, 0));
		pipeline.addLast("handler", new ChannelHandler());
	}
}
