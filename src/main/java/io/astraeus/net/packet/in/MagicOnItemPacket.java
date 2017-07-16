package io.astraeus.net.packet.in;

import io.astraeus.game.event.impl.MagicOnItemEvent;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

/**
 * The {@link IncomingPacket} responsible for using magic on inventory items.
 * 
 * @author SeVen
 */
@IncomingPacket.IncomingPacketOpcode(IncomingPacket.MAGIC_ON_ITEMS)
public final class MagicOnItemPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();

    final int slot = reader.readShort();
    final int itemId = reader.readShort(ByteModification.ADDITION);
    final int childId = reader.readShort();
    final int spellId = reader.readShort(ByteModification.ADDITION);

    player.post(new MagicOnItemEvent(itemId, slot, childId, spellId));
  }

}
