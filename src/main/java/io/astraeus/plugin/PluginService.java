package io.astraeus.plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.world.World;
import io.astraeus.util.LoggerUtils;

/**
 * The service that services plugins.
 * 
 * @author Vult-R
 */
public final class PluginService {

  /**
   * The single logger for this class.
   */
  private static final Logger logger = LoggerUtils.getLogger(PluginService.class);

  /**
   * The list of subscribers registered to the server.
   */
  private static final List<EventSubscriber<?>> subscribers = new ArrayList<>();

  /**
   * Loads the plugins.
   */
  public void load() {
    try {
      Collection<EventSubscriber<?>> plugins = findPlugins();

      plugins.stream().forEach(it -> register(it));      
    } catch (IOException e) {
      logger.log(Level.SEVERE, "A problem was encountered while trying to load plugins.", e);
    }
    logger.info("Loaded: " + subscribers.size() + " plugins.");
  }

  /**
   * Finds plugins in a given directory.
   *
   * @throws IOException
   */
  private Collection<EventSubscriber<?>> findPlugins() throws IOException {	  
    return findPlugins(new File("./plugins/src/main/java"));
  }

  /**
   * Finds plugins in a specified directory.
   * 
   * @param dir The directory to check for plugins.
   * 
   * @throws IOException
   * 
   * @return The collection of plugin data.
   */
  private Collection<EventSubscriber<?>> findPlugins(File dir) throws IOException {
    Collection<EventSubscriber<?>> plugins = new ArrayList<>();
    for (File file : dir.listFiles()) {
      String base = file.getPath(); 

      base = base.replace("\\", ".");
      
      base = base.replace("..plugins.src.main.java.", "");
      
      base = base.replace(".java", "");      

      if (!file.isDirectory()) {
        try {
          Class<?> clazz = Class.forName(base);
          
          if (EventSubscriber.class.isAssignableFrom(clazz) && !Modifier.isInterface(clazz.getModifiers()) && !Modifier.isAbstract(clazz.getModifiers())) {
              final EventSubscriber<?> sub = (EventSubscriber<?>) clazz.newInstance();

              plugins.add(sub);
          }
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        } catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
        
      } else {
        plugins.addAll(findPlugins(file));
      }

    }
    return Collections.unmodifiableCollection(plugins);
  }

  /**
   * Assigns a plugin to a subscriber
   * 
   * @param metas The meta deta for each plugin.
   */
  private void register(EventSubscriber<?> subscriber) {

    World.provideSubscriber(subscriber);

    subscribers.add(subscriber);

  }

  /**
   * Reloads plugins.
   */
  public void reload() {
    throw new UnsupportedOperationException("This is currently not supported.");
  }

  /**
   * Gets the list of subscriber registered to the server.
   */
  public List<EventSubscriber<?>> getSubscribers() {
    return subscribers;
  }

}
