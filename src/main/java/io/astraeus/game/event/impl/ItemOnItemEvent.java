package io.astraeus.game.event.impl;

import io.astraeus.game.event.Event;
import io.astraeus.game.world.entity.item.Item;

public final class ItemOnItemEvent implements Event {

  private final Item used;

  private final Item with;

  public ItemOnItemEvent(Item used, Item with) {
    this.used = used;
    this.with = with;
  }

  public Item getUsed() {
    return used;
  }

  public Item getUsedWith() {
    return with;
  }

}
