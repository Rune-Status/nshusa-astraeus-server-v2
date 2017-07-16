package io.astraeus.net.packet.in;

import io.astraeus.game.event.impl.ItemOnItemEvent;
import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

@IncomingPacket.IncomingPacketOpcode(IncomingPacket.ITEM_ON_ITEM)
public final class ItemOnItemPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();

    final int usedWithSlot = reader.readShort();
    final int itemUsedSlot = reader.readShort(ByteModification.ADDITION);

    final Item used = player.getInventory().get(itemUsedSlot);

    final Item with = player.getInventory().get(usedWithSlot);

    player.post(new ItemOnItemEvent(used, with));
  }

}

