package io.astraeus.game.event.impl;

import io.astraeus.game.event.Event;
import io.astraeus.game.world.entity.item.Item;

public final class ItemFirstClickEvent implements Event {

  private final Item item;

  private final int widgetId;

  public ItemFirstClickEvent(Item item, int widgetId) {
    this.item = item;
    this.widgetId = widgetId;
  }

  public Item getItem() {
    return item;
  }

  public int getWidgetId() {
    return widgetId;
  }

}
