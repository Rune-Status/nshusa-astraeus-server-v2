package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.PacketHeader;
import io.astraeus.net.packet.Sendable;

public final class SetWidgetStringPacket implements Sendable {

  private final String string;

  private final int id;

  public SetWidgetStringPacket(String string, int id) {
    this.string = string;
    this.id = id;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(126, PacketHeader.VARIABLE_SHORT);
    builder.writeString(string).writeShort(id, ByteModification.ADDITION);
    return builder.toOutgoingPacket();
  }
}
