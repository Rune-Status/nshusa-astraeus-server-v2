package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class DisplayInventoryWidgetPacket implements Sendable {

  private final int widgetId;

  private final int sidebarId;

  public DisplayInventoryWidgetPacket(int widgetId, int sidebarId) {

    this.widgetId = widgetId;
    this.sidebarId = sidebarId;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(248);
    builder.writeShort(widgetId, ByteModification.ADDITION).writeShort(sidebarId);
    return builder.toOutgoingPacket();
  }

}
