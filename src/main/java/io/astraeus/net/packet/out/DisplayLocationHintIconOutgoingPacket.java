package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class DisplayLocationHintIconOutgoingPacket implements Sendable {

  /**
   * The x coordinate for this location.
   */
  private final int x;

  /**
   * The y coordinate for this location.
   */
  private final int y;

  /**
   * The height for this location.
   */
  private final int height;

  /**
   * The direction to face.
   */
  private final int direction;

  /**
   * Creates a new {@link DisplayLocationHintIconOutgoingPacket}.
   * 
   * @param x The x coordinate for this icon.
   * 
   * @param y The y coordinate for this icon.
   * 
   * @param height The height for this icon.
   * 
   * @param direction The facing direction of the arrow.
   * 
   */
  public DisplayLocationHintIconOutgoingPacket(int x, int y, int height, int direction) {
    this.x = x;
    this.y = y;
    this.height = height;
    this.direction = direction;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(254);
    builder.write(direction).writeShort(x).writeShort(y).write(height);
    return builder.toOutgoingPacket();
  }

}
