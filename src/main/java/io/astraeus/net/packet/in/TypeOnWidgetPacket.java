package io.astraeus.net.packet.in;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.IncomingPacket.IncomingPacketOpcode;

@IncomingPacketOpcode({IncomingPacket.TYPE_ON_WIDGET})
public final class TypeOnWidgetPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {

  }

}
