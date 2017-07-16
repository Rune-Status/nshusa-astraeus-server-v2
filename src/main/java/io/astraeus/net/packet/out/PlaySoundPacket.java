package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class PlaySoundPacket implements Sendable {

  private final int id;

  private final int volume;

  private final int delay;

  public PlaySoundPacket(int id) {
    this(id, 50, 0);
  }

  public PlaySoundPacket(int id, int volume) {
    this(id, volume, 0);
  }

  public PlaySoundPacket(int id, int volume, int delay) {
    this.id = id;
    this.volume = volume;
    this.delay = delay;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(174);

    if (!player.attr().get(Player.SOUND_KEY) || id <= 0) {
      return Optional.empty();
    }

    builder.writeShort(id).write(volume).writeShort(delay);

    return builder.toOutgoingPacket();
  }

}
