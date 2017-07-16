package io.astraeus.net.packet.in;

import io.astraeus.game.event.impl.WidgetContainerFourthOptionEvent;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.IncomingPacket.IncomingPacketOpcode;

@IncomingPacketOpcode(IncomingPacket.WIDGET_CONTAINER_OPTION_4)
public final class WidgetContainerFourthOptionPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    final ByteBufReader reader = packet.getReader();
    final int itemSlot = reader.readShort(ByteModification.ADDITION);
    final int widgetId = reader.readShort();
    final int itemId = reader.readShort(ByteModification.ADDITION);

    player.post(new WidgetContainerFourthOptionEvent(widgetId, itemId, itemSlot));
  }

}
