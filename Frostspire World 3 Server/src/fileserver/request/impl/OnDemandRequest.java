package fileserver.request.impl;

import java.io.IOException;

import com.frostspire.GameLoader;

import fileserver.FileServerConstants;
import fileserver.request.Request;
import fileserver.response.impl.OnDemandResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

/**
 * Represents an Ondemand request. 
 * @author Professor Oak
 *
 */
public final class OnDemandRequest extends Request {

	/**
	 * The file type requested.
	 */
	private final int fileType;
	
	/**
	 * The id of the file requested.
	 */
	private final int fileId;
	
	/**
	 * Creates this request.
	 * @param channel	The channel which sent this request.
	 * @param fileType	The file type requested.
	 * @param fileId	The file id requested.
	 */
	public OnDemandRequest(Channel channel, int fileType, int fileId) {
		super(channel);
		this.fileType = fileType;
		this.fileId = fileId;
	}

	@Override
	public void dispatch() {
		
		//Attempt to load the requested file..
		ByteBuf file = null;
		try {
			file = GameLoader.getCache().getFile(fileType, fileId);			
		} catch (IOException e) {
			e.printStackTrace();
		}

		//If we loaded the file, send it.
		//Otherwise close the channel.
		if(file != null) {
			final int length = file.readableBytes();

			for (int chunk = 0; file.readableBytes() > 0; chunk++) {
				int chunkSize = file.readableBytes();
				if (chunkSize > FileServerConstants.MAX_ONDEMAND_CHUNK_LENGTH_BYTES) {
					chunkSize = FileServerConstants.MAX_ONDEMAND_CHUNK_LENGTH_BYTES;
				}

				byte[] tmp = new byte[chunkSize];
				file.readBytes(tmp, 0, tmp.length);
				ByteBuf chunkData = Unpooled.wrappedBuffer(tmp, 0, chunkSize);

				getChannel().writeAndFlush(new OnDemandResponse(chunkData, fileType, fileId, length, chunk));
			}
		
		} else {
			getChannel().close();
		}
	}
}
