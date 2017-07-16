package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class UpdateSkillPacket implements Sendable {

  private final int id;

  private final int level;

  private final int experience;

  public UpdateSkillPacket(int id, int level, int experience) {
    this.id = id;
    this.level = level;
    this.experience = experience;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(134);
    builder.write(id);
    builder.writeInt(experience, ByteOrder.MIDDLE);
    builder.write(level);
    return builder.toOutgoingPacket();
  }

}
