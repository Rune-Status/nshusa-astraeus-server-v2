package io.astraeus.game.world.entity.mob.player.widget;

import java.util.HashMap;
import java.util.Map;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.packet.out.DisplayChatBoxWidgetPacket;
import io.astraeus.net.packet.out.DisplayInventoryWidgetPacket;
import io.astraeus.net.packet.out.DisplayWidgetPacket;
import io.astraeus.net.packet.out.RemoveWidgetPacket;
import io.astraeus.net.packet.out.SetSideBarWidgetPacket;

/**
 * A specialized set for widgets.
 */
public final class WidgetSet {

  /**
   * A map of widgets and their types.
   */
  private final Map<WidgetType, Integer> widgets = new HashMap<>();

  /**
   * That player that this set belongs to.
   */
  private final Player player;

  /**
   * Creates a new {@link WidgetSet}.
   * 
   * @param player The player that this set belongs to.
   */
  public WidgetSet(Player player) {
    this.player = player;
  }

  public void openChatBoxWidget(int id) {
    widgets.clear();
    player.queuePacket(new DisplayChatBoxWidgetPacket(id));
    widgets.put(WidgetType.CHAT_BOX, id);
  }

  public void openInventoryWidget(int widgetId, int sidebarId) {
    widgets.clear();
    player.queuePacket(new DisplayInventoryWidgetPacket(widgetId, sidebarId));
    widgets.put(WidgetType.INVENTORY, widgetId);
  }

  public void openSidebarWidget(int tab, int sidebarId) {
    widgets.clear();
    player.queuePacket(new SetSideBarWidgetPacket(tab, sidebarId));
    widgets.put(WidgetType.TAB, sidebarId);
  }

  public void open(int id) {
    widgets.clear();
    player.queuePacket(new DisplayWidgetPacket(id));
    widgets.put(WidgetType.WINDOW, id);
  }

  /**
   * Closes any widgets that are open.
   */
  public void close() {
    widgets.clear();
    player.queuePacket(new RemoveWidgetPacket());
  }

  /**
   * Determines a widget is already present.
   */
  public boolean contains(int id) {
    return widgets.containsValue(id);
  }

  /**
   * Determines if a type of widget is already present.
   */
  public boolean contains(WidgetType type) {
    return widgets.containsKey(type);
  }

  /**
   * Determines if the map is empty.
   */
  public boolean isEmpty() {
    return widgets.isEmpty();
  }

}
