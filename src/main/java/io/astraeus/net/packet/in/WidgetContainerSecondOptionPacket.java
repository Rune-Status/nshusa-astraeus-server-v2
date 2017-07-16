package io.astraeus.net.packet.in;

import io.astraeus.game.event.impl.WidgetContainerSecondOptionEvent;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.IncomingPacket.IncomingPacketOpcode;

@IncomingPacketOpcode(IncomingPacket.WIDGET_CONTAINER_OPTION_2)
public final class WidgetContainerSecondOptionPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    final ByteBufReader reader = packet.getReader();
    final int widgetId = reader.readShort(ByteOrder.LITTLE, ByteModification.ADDITION);
    final int itemId = reader.readShort(ByteOrder.LITTLE, ByteModification.ADDITION);
    final int itemSlot = reader.readShort(ByteOrder.LITTLE);

    player.post(new WidgetContainerSecondOptionEvent(widgetId, itemId, itemSlot));
  }

}
