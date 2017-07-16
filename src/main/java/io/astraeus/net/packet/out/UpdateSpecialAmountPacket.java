package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class UpdateSpecialAmountPacket implements Sendable {

  private final int id;

  private final int amount;

  public UpdateSpecialAmountPacket(int id, int amount) {
    this.id = id;
    this.amount = amount;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(70);
    builder.writeShort(amount).writeShort(0, ByteOrder.LITTLE).writeShort(id, ByteOrder.LITTLE);
    return builder.toOutgoingPacket();
  }

}
