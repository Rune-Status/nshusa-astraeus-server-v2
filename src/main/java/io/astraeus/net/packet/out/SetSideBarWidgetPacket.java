package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class SetSideBarWidgetPacket implements Sendable {

  private final int tabId;

  private final int interfaceId;

  public SetSideBarWidgetPacket(int tabId, int interfaceId) {
    this.tabId = tabId;
    this.interfaceId = interfaceId;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(71);
    builder.writeShort(interfaceId).write(tabId, ByteModification.ADDITION);
    return builder.toOutgoingPacket();
  }

}
