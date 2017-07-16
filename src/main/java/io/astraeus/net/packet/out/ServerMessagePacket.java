package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.PacketHeader;
import io.astraeus.net.packet.Sendable;

/**
 * The {@link OutgoingPacket} that sends a message to a {@link Player}s chatbox.
 * 
 * @author SeVen
 */
public final class ServerMessagePacket implements Sendable {

  /**
   * The message to send.
   */
  private final String message;

  /**
   * Creates a new {@link ServerMessagePacket}.
   */
  public ServerMessagePacket(String message) {
    this.message = message;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(253, PacketHeader.VARIABLE_BYTE);
    builder.writeString(message);
    return builder.toOutgoingPacket();
  }

}
