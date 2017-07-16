package io.astraeus.net.packet.in;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

@IncomingPacket.IncomingPacketOpcode(IncomingPacket.ENTER_REGION)
public final class EnterRegionPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {

  }

}
