package io.astraeus.net.packet.in;

import io.astraeus.game.event.impl.WidgetContainerThirdOptionEvent;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.IncomingPacket.IncomingPacketOpcode;

@IncomingPacketOpcode(IncomingPacket.WIDGET_CONTAINER_OPTION_3)
public final class WidgetContainerThirdOptionPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    final ByteBufReader reader = packet.getReader();
    final int widgetId = reader.readShort(ByteOrder.LITTLE);
    final int itemId = reader.readShort(ByteModification.ADDITION);
    final int itemSlot = reader.readShort(ByteModification.ADDITION);

    player.post(new WidgetContainerThirdOptionEvent(widgetId, itemId, itemSlot));
  }

}
