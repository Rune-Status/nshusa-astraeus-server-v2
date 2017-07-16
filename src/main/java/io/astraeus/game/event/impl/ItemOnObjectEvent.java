package io.astraeus.game.event.impl;

import io.astraeus.game.event.Event;
import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.object.GameObject;

public final class ItemOnObjectEvent implements Event {

  private final Item item;

  private final GameObject gameObject;

  public ItemOnObjectEvent(Item item, GameObject gameObject) {
    this.item = item;
    this.gameObject = gameObject;
  }

  public Item getItem() {
    return item;
  }

  public GameObject getGameObject() {
    return gameObject;
  }

}
