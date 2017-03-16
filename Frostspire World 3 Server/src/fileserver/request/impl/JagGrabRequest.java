package fileserver.request.impl;

import com.frostspire.GameLoader;

import fileserver.request.Request;
import fileserver.response.impl.JagGrabResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;

/**
 * Represents a JAGGRAB request.
 * @author Professor Oak
 */
public final class JagGrabRequest extends Request {

	/**
	 * The path to the file.
	 */
	private final String path;

	/**
	 * Creates this request.
	 * @param channel	The channel which sent this request.
	 * @param path 	The file path.
	 */
	public JagGrabRequest(Channel channel, String path) {
		super(channel);
		this.path = path;
	}

	@Override
	public void dispatch() {
		
		//Attempt to get the file that's been requested..
		ByteBuf file = GameLoader.getCache().request(path);

		//If we loaded the file, send it.
		//Otherwise close the channel.
		if (file != null) {
			getChannel().writeAndFlush(new JagGrabResponse(file)).addListener(ChannelFutureListener.CLOSE);
		} else {
			getChannel().close();
		}
	}
}
