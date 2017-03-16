package com.frostspire;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.frostspire.cache.CacheLoader;
import com.frostspire.cache.impl.definitions.ItemDefinition;
import com.frostspire.cache.impl.definitions.NPCSpawns;
import com.frostspire.cache.impl.definitions.NpcDefinition;
import com.frostspire.cache.impl.definitions.ObjectDefinition;
import com.frostspire.cache.impl.definitions.ShopDefinition;
import com.frostspire.cache.impl.definitions.WeaponInterfaces;
import com.frostspire.engine.GameEngine;
import com.frostspire.engine.task.impl.CombatPoisonEffect.CombatPoisonData;
import com.frostspire.net.NetworkConstants;
import com.frostspire.net.channel.ChannelPipelineHandler;
import com.frostspire.util.ShutdownHook;
import com.frostspire.world.collision.region.RegionClipping;
import com.frostspire.world.content.clan.ClanChatManager;
import com.frostspire.world.content.skill.fletching.fletchable.impl.Arrow;
import com.frostspire.world.content.skill.fletching.fletchable.impl.Bolt;
import com.frostspire.world.content.skill.fletching.fletchable.impl.Carvable;
import com.frostspire.world.content.skill.fletching.fletchable.impl.Crossbow;
import com.frostspire.world.content.skill.fletching.fletchable.impl.Featherable;
import com.frostspire.world.content.skill.fletching.fletchable.impl.Stringable;
import com.frostspire.world.model.dialogue.DialogueManager;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import fileserver.FileServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetector.Level;

/**
 * The starting point of Elvarg.
 * @author Swiffy
 * @author Professor Oak
 */
public class GameLoader {

	/**
	 * The game engine, executed by {@link ScheduledExecutorService}.
	 * The game engine's cycle rate is normally 600 ms.
	 */
	private static final GameEngine engine = new GameEngine();
	
	/**
	 * The cache loader.
	 * Loads the client's cache files.
	 * Used for definitions, clipping, etc..
	 */
	private static final CacheLoader cacheLoader = new CacheLoader();
	
	/**
	 * Is the server currently updating?
	 */
	private static boolean updating;
	
	/**
	 * The main logger.
	 */
	private static final Logger logger = Logger.getLogger("GameLoader");

	public static void main(String[] params) {
		Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		try {
			logger.info("Initializing the game...");
			
			final ExecutorService serviceLoader = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("GameLoadingThread").build());
					
			//Load the cache..
			serviceLoader.execute(() -> {
				try {
					cacheLoader.init();
					
					//JAGGRAB
					if(GameConstants.JAGGRAB_ENABLED) {
						FileServer.init();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			//DEFINITIONS
			logger.info("Loading definitions...");
			serviceLoader.execute(() -> ItemDefinition.init());
			serviceLoader.execute(() -> NpcDefinition.loadNPCDefinitions());
			serviceLoader.execute(() -> NPCSpawns.NPCSpawnsLoader().load());
			serviceLoader.execute(() -> ObjectDefinition.init());
			serviceLoader.execute(() -> RegionClipping.init());
			serviceLoader.execute(() -> ObjectDefinition.parseObjects().load());
			serviceLoader.execute(() -> ShopDefinition.parseShops().load());
			serviceLoader.execute(() -> WeaponInterfaces.parseInterfaces().load());
			serviceLoader.execute(() -> DialogueManager.parseDialogues().load());
			
			//OTHERS
			serviceLoader.execute(() -> ClanChatManager.init());
			serviceLoader.execute(() -> CombatPoisonData.init());
			serviceLoader.execute(() -> Arrow.declare());
			serviceLoader.execute(() -> Bolt.declare());
			serviceLoader.execute(() -> Carvable.declare());
			serviceLoader.execute(() -> Crossbow.declare());
			serviceLoader.execute(() -> Featherable.declare());
			serviceLoader.execute(() -> Stringable.declare());
			
			//Shutdown the loader
			serviceLoader.shutdown();
			
			//Make sure the loader is properly shut down
			if (!serviceLoader.awaitTermination(15, TimeUnit.MINUTES))
				throw new IllegalStateException("The background service load took too long!");        

			//Bind the port...
			logger.info("Binding port "+NetworkConstants.GAME_PORT+"...");
			ResourceLeakDetector.setLevel(Level.DISABLED);
			EventLoopGroup loopGroup = new NioEventLoopGroup();
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(loopGroup).channel(NioServerSocketChannel.class)
			.childHandler(new ChannelPipelineHandler()).bind(NetworkConstants.GAME_PORT).syncUninterruptibly();
			
			
			//Start the game engine using a {@link ScheduledExecutorService}
			logger.info("Starting game engine...");
			final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().setNameFormat("GameThread").build());
			executor.scheduleAtFixedRate(engine, 0, GameConstants.GAME_ENGINE_PROCESSING_CYCLE_RATE, TimeUnit.MILLISECONDS);
					
			logger.info("The loader has finished loading utility tasks.");
			logger.info("Frostspire World 3 is now online on port "+NetworkConstants.GAME_PORT+"!");
		} catch (Exception ex) {
			logger.log(java.util.logging.Level.SEVERE, "Could not start Frostspire World 3! Program terminated.", ex);
			System.exit(1);
		}
	}

	public static CacheLoader getCache() {
		return cacheLoader;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setUpdating(boolean updating) {
		GameLoader.updating = updating;
	}

	public static boolean isUpdating() {
		return GameLoader.updating;
	}
}