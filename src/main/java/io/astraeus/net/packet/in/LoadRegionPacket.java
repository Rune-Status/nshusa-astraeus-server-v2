package io.astraeus.net.packet.in;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.attr.AttributeKey;
import io.astraeus.game.world.entity.object.GameObjects;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

@IncomingPacket.IncomingPacketOpcode(IncomingPacket.LOADED_REGION)
public final class LoadRegionPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {

    player.attr().put(AttributeKey.valueOf("save", false), true);

    GameObjects.createGlobalObjects(player);
    // TODO this needs to be done better, load ground items for players entering regions
    // GameObjects.createGlobalItems(player);
  }

}
