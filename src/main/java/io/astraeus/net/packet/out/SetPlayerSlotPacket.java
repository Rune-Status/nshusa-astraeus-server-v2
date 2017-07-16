package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class SetPlayerSlotPacket implements Sendable {

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {

    GamePacketBuilder builder = new GamePacketBuilder(249);

    builder.write(1, ByteModification.ADDITION).writeShort(player.getSlot(),
        ByteModification.ADDITION, ByteOrder.LITTLE);
    return builder.toOutgoingPacket();
  }

}
