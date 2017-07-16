package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class DisplayWidgetPacket implements Sendable {

  private final int interfaceId;

  public DisplayWidgetPacket(int interfaceId) {
    this.interfaceId = interfaceId;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(97);
    builder.writeShort(interfaceId);
    return builder.toOutgoingPacket();
  }

}
