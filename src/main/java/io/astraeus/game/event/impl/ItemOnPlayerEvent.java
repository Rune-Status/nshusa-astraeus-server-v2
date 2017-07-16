package io.astraeus.game.event.impl;

import io.astraeus.game.event.Event;
import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.mob.player.Player;

public final class ItemOnPlayerEvent implements Event {

  private final Item used;

  private final Player usedWith;

  public ItemOnPlayerEvent(Item used, Player usedWith) {
    this.used = used;
    this.usedWith = usedWith;
  }

  public Item getUsed() {
    return used;
  }

  public Player getUsedWith() {
    return usedWith;
  }

}
