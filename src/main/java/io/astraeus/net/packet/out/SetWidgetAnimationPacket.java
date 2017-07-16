package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class SetWidgetAnimationPacket implements Sendable {

  private final int interfaceId;

  private final int animationId;

  public SetWidgetAnimationPacket(int interfaceId, int animationId) {
    this.interfaceId = interfaceId;
    this.animationId = animationId;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(200);
    builder.writeShort(interfaceId).writeShort(animationId);
    return builder.toOutgoingPacket();
  }

}
