package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class UpdateMapRegion implements Sendable {

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(73);
    player.setLastPosition(player.getPosition().copy());
    builder.writeShort(player.getPosition().getRegionalX() + 6, ByteModification.ADDITION)
        .writeShort(player.getPosition().getRegionalY() + 6);
    return builder.toOutgoingPacket();
  }

}
