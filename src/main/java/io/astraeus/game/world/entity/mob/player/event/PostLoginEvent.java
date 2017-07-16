package io.astraeus.game.world.entity.mob.player.event;

import io.astraeus.game.event.Event;
import io.astraeus.game.world.entity.mob.player.Player;

public final class PostLoginEvent implements Event {

  private final Player player;

  public PostLoginEvent(Player player) {
    this.player = player;
  }

  public Player getPlayer() {
    return player;
  }

}
