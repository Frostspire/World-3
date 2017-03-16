package fileserver.net;

import com.frostspire.net.NetworkConstants;

import fileserver.net.codec.OnDemandDecoder;
import fileserver.net.codec.OnDemandEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * A {@link ChannelInitializer} for an OnDemand channel.
 * @author Graham
 * @author Professor Oak
 */
public final class OnDemandPipelineFactory extends ChannelInitializer<SocketChannel> {

	/**
	 * The channel event handler.
	 */
	private final ChannelHandler handler;
	
	/**
	 * Creates an OnDemand pipeline factory.
	 * @param handler The channel event handler.
	 */
	public OnDemandPipelineFactory(ChannelHandler handler) {
		this.handler = handler;
	}
	
	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		final ChannelPipeline pipeline = channel.pipeline();
				
		// decoders
		pipeline.addLast("decoder", new OnDemandDecoder());
		
		// encoders
		pipeline.addLast("encoder", new OnDemandEncoder());
		
		// handler
		pipeline.addLast("timeout", new IdleStateHandler(NetworkConstants.SESSION_TIMEOUT, 0, 0));
		pipeline.addLast("handler", handler);
	}
}
