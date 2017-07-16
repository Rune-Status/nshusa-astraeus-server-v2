package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

/**
 * The {@link OutgoingPacket} that sends a system update.
 * 
 * @author SeVen
 */
public final class UpdateServerPacket implements Sendable {

  /**
   * The amount of seconds.
   */
  private int seconds;

  /**
   * Creates a new {@link UpdateServerPacket}.
   * 
   * @param seconds The amount of seconds before a system update occurs.
   */
  public UpdateServerPacket(int seconds) {
    this.seconds = seconds;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(114);
    builder.writeShort(seconds * 50 / 30, ByteOrder.LITTLE);
    return builder.toOutgoingPacket();
  }

}
