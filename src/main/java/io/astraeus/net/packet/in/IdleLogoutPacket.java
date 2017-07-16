package io.astraeus.net.packet.in;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.IncomingPacket.IncomingPacketOpcode;

/**
 * The {@link IncomingPacket} responsible logging out a player after a certain amount of time.
 * 
 * @author SeVen
 */
@IncomingPacketOpcode(IncomingPacket.IDLE_LOGOUT)
public final class IdleLogoutPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {

  }
}
