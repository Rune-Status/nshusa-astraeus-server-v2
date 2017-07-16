package io.astraeus.net.packet.in;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

/**
 * The {@link IncomingPacket} responsible reporting another player.
 * 
 * @author SeVen
 */
@IncomingPacket.IncomingPacketOpcode(IncomingPacket.REPORT_PLAYER)
public final class ReportPlayerPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {

  }
}
