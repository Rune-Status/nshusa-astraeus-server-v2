package io.astraeus.net.packet.in;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

/**
 * The {@link IncomingPacket} responsible for various clicking in-game.
 * 
 * @author SeVen
 */
@IncomingPacket.IncomingPacketOpcode({IncomingPacket.CAMERA_MOVEMENT, IncomingPacket.IDLE_LOGOUT,
    IncomingPacket.FOCUS_CHANGE})
public class DefaultPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {

  }

}
