package io.astraeus;

import java.util.logging.Logger;

import io.astraeus.net.NetworkConstants;
import io.astraeus.net.channel.PipelineFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetector.Level;

/**
 * The main class of the FileServer. Services are started here..
 * 
 * @author Professor Oak
 * 
 *         Credits to:
 * @author Graham
 * @author Nikki For references from their update-server in the Apollo source.
 */
public final class FileServer {

	/**
	 * The logger for this class.
	 */
	private static final Logger logger = Logger.getLogger(FileServer.class.getName());

	/**
	 * Starts the FileServer system.
	 * 
	 * @throws Exception
	 *             if an error occurs.
	 */
	public static void init() throws Exception {

		logger.info("Starting File-Server service on port " + NetworkConstants.JAGGRAB_PORT + "..");

		// Bind service..
		ResourceLeakDetector.setLevel(Level.DISABLED);
		EventLoopGroup loopGroup = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(loopGroup).channel(NioServerSocketChannel.class).childHandler(new PipelineFactory())
				.bind(NetworkConstants.JAGGRAB_PORT).syncUninterruptibly();

		logger.info("File-Server has been initialised on port " + NetworkConstants.JAGGRAB_PORT + ".");
	}
}
