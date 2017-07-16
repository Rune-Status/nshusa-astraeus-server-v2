package io.astraeus.net.packet.in;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.IncomingPacket.IncomingPacketOpcode;

@IncomingPacketOpcode(IncomingPacket.WIDGET_CONTAINER_OPTION_5)
public final class WidgetContainerFifthOptionPacket implements Receivable {

  @SuppressWarnings("unused")
  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();

    int itemSlot = reader.readShort(ByteOrder.LITTLE);
    int interfaceId = reader.readShort(ByteModification.ADDITION);
    int itemId = reader.readShort(ByteOrder.LITTLE);
  }

}
