package io.astraeus.net.packet.in;

import io.astraeus.game.event.impl.WidgetContainerFirstOptionEvent;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.IncomingPacket.IncomingPacketOpcode;

@IncomingPacketOpcode(IncomingPacket.WIDGET_CONTAINER_OPTION_1)
public final class WidgetContainerFirstOptionPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    final ByteBufReader reader = packet.getReader();

    final int widgetId = reader.readShort(ByteModification.ADDITION);
    final int itemSlot = reader.readShort(ByteModification.ADDITION);
    final int itemId = reader.readShort(ByteModification.ADDITION);

    player.post(new WidgetContainerFirstOptionEvent(widgetId, itemId, itemSlot));
  }

}
