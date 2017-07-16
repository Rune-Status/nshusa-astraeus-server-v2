package io.astraeus.net.packet.in;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

/**
 * The {@link IncomingPacket} responsible for using magic on ground items.
 * 
 * @author SeVen
 */
@IncomingPacket.IncomingPacketOpcode(181)
public class MagicOnFloorItemPacket implements Receivable {

  @SuppressWarnings("unused")
  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();

    final int itemY = reader.readShort(ByteOrder.LITTLE);
    final int itemId = reader.readShort(false);
    final int itemX = reader.readShort(ByteOrder.LITTLE);
    final int spellId = reader.readShort(false, ByteModification.ADDITION);

  }

}
