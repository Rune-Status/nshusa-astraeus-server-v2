package io.astraeus;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.astraeus.game.world.World;
import io.astraeus.util.LoggerUtils;
import io.astraeus.util.Stopwatch;
import lombok.Getter;

/**
 * The core class of the server.
 *
 * @author Vult-R
 */
public final class Server {

	/**
	 * The single logger for this class.
	 */
	private static final Logger logger = LoggerUtils.getLogger(Server.class);

	/**
	 * To check if the server needs to be updated.
	 */
	public static boolean updateServer = false;

	/**
	 * Determines if the server has started.
	 */
	public static boolean serverStarted = false;

	/**
	 * The elapsed time the server has been running.
	 */
	@Getter
	private static Stopwatch runtime;

	/**
	 * The main entry point to the server.
	 *
	 * @param args
	 *            The command line arguments.
	 *
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		final Stopwatch timer = Stopwatch.start();

		World world = new World(args.length > 0 ? Integer.parseInt(args[0]) : 1);

		logger.info(String.format("Starting world: %d", world.getId()));

		new Bootstrap(world).build().bind();

		runtime = Stopwatch.start();

		logger.info(String.format("World %d initialized. [Took %s ms]", world.getId(), timer.elapsed(TimeUnit.MILLISECONDS)));

	}

	/**
	 * Gets the elapsed time the server has been running for.
	 * 
	 * @return The elapsed time.
	 */
	public static String getUptime() {
		long elapsedSeconds = Server.getRuntime().elapsed(TimeUnit.SECONDS);

		long minute = elapsedSeconds >= 60 ? elapsedSeconds / 60 : 0;

		long seconds = (Server.getRuntime().elapsed(TimeUnit.SECONDS) >= 60
				? Server.getRuntime().elapsed(TimeUnit.SECONDS) - (minute * 60)
				: Server.getRuntime().elapsed(TimeUnit.SECONDS));

		long hour = minute >= 60 ? minute / 60 : 0;
		long day = hour >= 24 ? hour / 24 : 0;

		return String.format("[uptime= %d Days %d Hours %d Minutes %d Seconds]", day, hour, minute, seconds);
	}

}
