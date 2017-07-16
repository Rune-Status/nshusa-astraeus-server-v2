package io.astraeus.net.packet.in;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.IncomingPacket.IncomingPacketOpcode;

/**
 * The {@link IncomingPacket} responsible for dialogues.
 * 
 * @author SeVen
 */
@IncomingPacketOpcode(40)
public final class DialoguePacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {

    player.getDialogueFactory().execute();

  }
}
