package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class ResetCameraPositionPacket implements Sendable {

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    return new GamePacketBuilder(107).toOutgoingPacket();
  }

}
