package io.astraeus.net.packet.in;

import io.astraeus.game.event.impl.ItemThirdClickEvent;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

@IncomingPacket.IncomingPacketOpcode(IncomingPacket.ITEM_OPTION_3)
public final class ItemThirdOptionPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    final ByteBufReader reader = packet.getReader();
    final int itemId11 = reader.readShort(ByteOrder.LITTLE);
    final int itemId1 = reader.readShort(ByteModification.ADDITION);
    final int itemId = reader.readShort(ByteModification.ADDITION);

    player.post(new ItemThirdClickEvent(itemId, itemId11, itemId1));
  }

}
