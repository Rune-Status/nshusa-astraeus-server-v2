package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

/**
 * The {@link OutgoingPacket} responsible for changing settings on a client.
 * 
 * @author SeVen
 */
public final class SetWidgetConfigPacket implements Sendable {

  /**
   * The configuration id.
   */
  private final int id;

  /**
   * The value to change.
   */
  private final int value;

  /**
   * Creates a new {@link SetWidgetConfigPacket}.
   * 
   * @param id The configuration id.
   * 
   * @param value The value to change.
   */
  public SetWidgetConfigPacket(int id, int value) {
    this.id = id;
    this.value = value;
  }

  /**
   * Creates a new {@link SetWidgetConfigPacket}.
   * 
   * @param id The configuration id.
   */
  public SetWidgetConfigPacket(int id, boolean enabled) {
    this(id, enabled ? 1 : 0);
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(36);
    builder.writeShort(id, ByteOrder.LITTLE).write(value);
    return builder.toOutgoingPacket();
  }

}
