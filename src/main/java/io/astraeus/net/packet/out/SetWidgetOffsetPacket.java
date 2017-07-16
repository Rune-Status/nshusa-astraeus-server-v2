package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class SetWidgetOffsetPacket implements Sendable {

  private final int id;
  private final int x, y;

  public SetWidgetOffsetPacket(int x, int y, int id) {
    this.x = x;
    this.y = y;
    this.id = id;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(70);
    builder.writeShort(x).writeShort(y, ByteOrder.LITTLE).writeShort(id, ByteOrder.LITTLE);
    return builder.toOutgoingPacket();
  }

}
