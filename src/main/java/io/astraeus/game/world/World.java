package io.astraeus.game.world;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import io.astraeus.game.GameConstants;
import io.astraeus.game.event.Event;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.UniversalEventProvider;
import io.astraeus.game.task.Task;
import io.astraeus.game.task.TaskManager;
import io.astraeus.game.world.entity.mob.Mob;
import io.astraeus.game.world.entity.mob.MobList;
import io.astraeus.game.world.entity.mob.npc.Npc;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.event.RegisterPlayerEvent;
import io.astraeus.plugin.PluginService;
import io.astraeus.util.LoggerUtils;
import lombok.Getter;

/**
 * Represents the game world.
 * 
 * @author Vult-R
 */
public final class World {

  /**
   * The single logger for this class.
   */
  public final Logger logger = LoggerUtils.getLogger(World.class);

  /**
   * The collection of players in the game world.
   */
  @Getter private static final MobList<Player> players = new MobList<Player>(GameConstants.MAX_PLAYERS);

  /**
   * The collection of npcs in the game world.
   */
  @Getter private static final MobList<Npc> npcs = new MobList<Npc>(GameConstants.MAX_NPC_SPAWNS);

  /**
   * The {@link Set} of banned hosts.
   */
  @Getter private static final Set<String> ipBans = new HashSet<>();

  /**
   * The {@link Set} of banned unique ids
   */
  @Getter private static final Set<Integer> bannedUids = new HashSet<>();

  /**
   * The {@link Player}s waiting to login.
   */
  @Getter private static final Queue<Player> logins = new ConcurrentLinkedQueue<>();

  /**
   * The {@link Player}s waiting to logout.
   */
  @Getter private static final Queue<Player> logouts = new ConcurrentLinkedQueue<>();

  /**
   * This worlds event provider.
   */
  @Getter private static final UniversalEventProvider eventProvider = new UniversalEventProvider();

  /**
   * The service for plugins.
   */
  @Getter private static final PluginService pluginService = new PluginService();

  /**
   * The tasks for this world.
   */
  @Getter private final static TaskManager tasks = new TaskManager();
  
  @Getter private final int id;
  
  @Getter private final int port;
  
  public World(int id) {
    this.id = id;
    this.port = 43593 + id;
  }

  /**
   * Registers and adds a {@code entity) into the game world.
   * 
   * @param entity The entity to add.
   */
  public static void register(Mob entity) {
    // check to make this entity is not registered already, and is present.
    if (entity == null || entity.isRegistered()) {
      return;
    }

    if (entity.isPlayer()) {
      Player player = (Player) entity;
      player.setId(-1);
      players.add(player);
    }
  }

  /**
   * Deregisters a {@link Mob} from the game world.
   * 
   * @param entity The entity to remove.
   */
  public static void deregister(Mob entity) {

    if (entity == null || !entity.isRegistered()) {
      return;
    }

    if (entity.isPlayer()) {
      players.remove(entity);
    } else {
      npcs.remove(entity);
    }
  }

  /**
   * The function that makes a player wait until they can be added into the game.
   * 
   * @param player
   */
  public static void queueLogin(Player player) {
    if (player.getSession() != null && !logins.contains(player)) {
      logins.add(player);
    }
  }

  /**
   * The function that allows players to login.
   */
  public static void dequeueLogin() {
    for (int index = 0; index < GameConstants.LOGIN_LIMIT; index++) {
      Player player = logins.poll();

      if (player == null) {
        break;
      }

      post(player, new RegisterPlayerEvent(player));
    }
  }

  /**
   * The function that makes a player wait until they can be logged out in sync with the server.
   * 
   * @param player
   */
  public static void queueLogout(Player player) {
    if (player != null && !logouts.contains(player)) {
      logouts.add(player);
    }
  }

  /**
   * The function that logs out players from the game world.
   */
  public static void dequeueLogout() {
    for (int index = 0; index < logouts.size(); index++) {
      Player player = logouts.peek();

      if (player == null || index >= GameConstants.LOGOUT_LIMIT) {
        break;
      }
      
      logouts.poll();

      player.onDeregister();
    }
  }

  /**
   * Searches the collection of players and retrieves the player with the specified name
   * 
   * @param name
   */
  public static Optional<Player> searchPlayer(String name) {
    return players.stream().filter(Objects::nonNull)
        .filter(it -> name.equalsIgnoreCase(it.getUsername())).findFirst();
  }

  /**
   * Posts an event to this worlds event provider.
   *
   * @param player The player to post the event for.
   * @param event The event to post.
   */
  public static <E extends Event> void post(Player player, E event) {
    eventProvider.post(player, event);
  }

  /**
   * Provides an event subscriber to this worlds event provider.
   *
   * @param subscriber The event subscriber.
   */
  public static <E extends Event> void provideSubscriber(EventSubscriber<E> subscriber) {
    eventProvider.provideSubscriber(subscriber);
  }

  /**
   * Deprives an event subscriber to this worlds event provider.
   *
   * @param subscriber The event subscriber.
   */
  public static <E extends Event> void depriveSubscriber(EventSubscriber<E> subscriber) {
    eventProvider.depriveSubscriber(subscriber);
  }

  /**
   * Submits a new {@link Task}.
   * 
   * @param task The task to execute.
   */
  public static void submit(Task task) {
    tasks.queue(task);
  }

}
